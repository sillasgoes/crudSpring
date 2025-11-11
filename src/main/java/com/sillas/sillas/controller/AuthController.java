package com.sillas.sillas.controller;

import com.sillas.sillas.config.jwt.TokenConfig;
import com.sillas.sillas.entities.dto.LoginRequest;
import com.sillas.sillas.entities.dto.LoginResponse;
import com.sillas.sillas.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final TokenConfig tokenConfig;

    @PostMapping
    @RequestMapping("/login")
    public LoginResponse login(LoginRequest request){
        var token = authService.login(request);
        return new LoginResponse(token);
    }
}
