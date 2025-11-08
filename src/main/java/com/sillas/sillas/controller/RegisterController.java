package com.sillas.sillas.controller;


import com.sillas.sillas.entities.dto.RegisterRequest;
import com.sillas.sillas.entities.dto.RegisterResponse;
import com.sillas.sillas.service.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/register")
public class RegisterController {

    private final RegisterService registerService;

    @PostMapping
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest request) {
        System.out.println("Iniciando registro" + request.toString());
        var response = registerService.register(request);
        return ResponseEntity.ok(response);
    }
}
