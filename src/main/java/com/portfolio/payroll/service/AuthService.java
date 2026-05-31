package com.portfolio.payroll.service;

import com.portfolio.payroll.dto.AuthRequest;
import com.portfolio.payroll.dto.AuthResponse;
import com.portfolio.payroll.entity.User;
import com.portfolio.payroll.exception.DuplicateResourceException;
import com.portfolio.payroll.repository.UserRepository;
import com.portfolio.payroll.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(AuthRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new DuplicateResourceException("User", "username", request.getUsername());
        }
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role("VIEWER")
                .enabled(true)
                .build();
        userRepository.save(user);

        var userDetails = org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();

        String token = jwtService.generateToken(userDetails);
        return AuthResponse.builder()
                .token(token)
                .username(user.getUsername())
                .role(user.getRole())
                .expiresIn(86400000)
                .build();
    }
    public AuthResponse registerAdmin(AuthRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new DuplicateResourceException("User", "username", request.getUsername());
        }
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role("ADMIN")
                .enabled(true)
                .build();
        userRepository.save(user);

        var userDetails = org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();

        String token = jwtService.generateToken(userDetails);
        return AuthResponse.builder()
                .token(token)
                .username(user.getUsername())
                .role(user.getRole())
                .expiresIn(86400000)
                .build();
    }

    public AuthResponse login(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow();

        var userDetails = org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();

        String token = jwtService.generateToken(userDetails);
        return AuthResponse.builder()
                .token(token)
                .username(user.getUsername())
                .role(user.getRole())
                .expiresIn(86400000)
                .build();
    }
}