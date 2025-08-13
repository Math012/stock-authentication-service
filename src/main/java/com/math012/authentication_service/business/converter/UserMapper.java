package com.math012.authentication_service.business.converter;

import com.math012.authentication_service.business.dto.request.UserRequestDTO;
import com.math012.authentication_service.business.dto.response.UserResponseDTO;
import com.math012.authentication_service.infra.model.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserEntity forUserEntityFromUserRequestDTO(UserRequestDTO dto);
    UserResponseDTO forUserResponseDTOFromUserEntity(UserEntity entity);
}