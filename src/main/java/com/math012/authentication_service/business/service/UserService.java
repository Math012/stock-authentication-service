package com.math012.authentication_service.business.service;

import com.math012.authentication_service.business.converter.UserMapper;
import com.math012.authentication_service.business.dto.request.UserRequestDTO;
import com.math012.authentication_service.business.dto.response.UserResponseDTO;
import com.math012.authentication_service.infra.exception.EmailAlreadyRegisteredException;
import com.math012.authentication_service.infra.exception.InvalidFieldsException;
import com.math012.authentication_service.infra.exception.ResourceNotFoundException;
import com.math012.authentication_service.infra.repository.UserRepository;
import com.math012.authentication_service.infra.security.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final UserMapper mapper;

    public UserResponseDTO registerUser(UserRequestDTO userRequestDTO){
        try {
            verifyFields(userRequestDTO);
            verifyEmailExists(userRequestDTO);
            userRequestDTO.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
            return mapper.forUserResponseDTOFromUserEntity(repository.save(mapper.forUserEntityFromUserRequestDTO(userRequestDTO)));
        }catch (NullPointerException e){
            throw new InvalidFieldsException("Solicitação inválida: campos inválidos");
        }
    }

    public String login(UserRequestDTO userRequestDTO){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userRequestDTO.getEmail(),userRequestDTO.getPassword())
        );
        return "Bearer " + jwtUtil.generateToken(authentication.getName());
    }

    public UserResponseDTO findUserByEmail(String email){
        try {
            var entity = repository.findByEmail(email);
            if (Objects.isNull(entity)) throw new ResourceNotFoundException("Erro ao localizar o usuário com o e-mail: " + email + ", tente novamente!");
            return mapper.forUserResponseDTOFromUserEntity(entity);
        }catch (NullPointerException e){
            throw new InvalidFieldsException("Solicitação inválida: campos inválidos");
        }
    }

    public void verifyEmailExists(UserRequestDTO userRequestDTO){
        if (repository.findByEmail(userRequestDTO.getEmail()) != null){
            throw new EmailAlreadyRegisteredException("Erro ao cadastrar usuário: e-mail " + userRequestDTO.getEmail()+" já está cadastrado!");
        }
    }

    public void verifyFields(UserRequestDTO dto){
        if (dto.getEmail().isBlank() || dto.getPassword().isBlank()) throw new InvalidFieldsException("Solicitação inválida: campos inválidos");
    }
}