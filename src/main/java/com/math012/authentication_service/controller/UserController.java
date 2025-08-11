package com.math012.authentication_service.controller;

import com.math012.authentication_service.business.dto.request.UserRequestDTO;
import com.math012.authentication_service.business.dto.response.UserResponseDTO;
import com.math012.authentication_service.business.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/authentication")
public class UserController {

    private final UserService service;


    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@RequestBody UserRequestDTO userRequestDTO){
        return ResponseEntity.ok(service.registerUser(userRequestDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserRequestDTO userRequestDTO){
        return ResponseEntity.ok(service.login(userRequestDTO));
    }

    @GetMapping("/find/{email}")
    public ResponseEntity<UserResponseDTO> findByEmail(@PathVariable("email")String email){
        return ResponseEntity.ok(service.findUserByEmail(email));
    }
}