package com.math012.authentication_service.business.dto.request;

import jakarta.persistence.Column;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class UserRequestDTO {
    private String email;
    private String password;
}