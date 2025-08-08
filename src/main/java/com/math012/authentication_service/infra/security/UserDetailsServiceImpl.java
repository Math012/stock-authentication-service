package com.math012.authentication_service.infra.security;

import com.math012.authentication_service.infra.model.UserEntity;
import com.math012.authentication_service.infra.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@AllArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity entity = repository.findByEmail(email);
        if (Objects.isNull(entity)) throw new RuntimeException("Erro ao consultar email");
        return User.withUsername(entity.getUsername())
                .password(entity.getPassword())
                .authorities(entity.getAuthorities())
                .build();
    }
}