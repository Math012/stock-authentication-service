package com.math012.authentication_service.business.dto.response;

import jakarta.persistence.Column;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class UserResponseDTO {
    private Long id;
    private String email;
    private String password;
}