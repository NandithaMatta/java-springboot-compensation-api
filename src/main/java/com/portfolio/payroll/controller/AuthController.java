package com.portfolio.payroll.controller;

import com.portfolio.payroll.dto.AuthRequest;
import com.portfolio.payroll.dto.AuthResponse;
import com.portfolio.payroll.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(
            @Valid @RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/register/admin")
    public ResponseEntity<AuthResponse> registerAdmin(
            @Valid @RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.registerAdmin(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @Valid @RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}