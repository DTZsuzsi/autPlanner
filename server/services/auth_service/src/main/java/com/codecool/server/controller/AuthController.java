package com.codecool.server.controller;


import com.codecool.server.security.JWTUtil;
import com.codecool.server.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private JWTUtil jwtUtil;
    private AuthService authService;
    @Autowired
    public AuthController(JWTUtil jwtUtil, AuthService authService) {
        this.jwtUtil = jwtUtil;
        this.authService = authService;
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        // Implement login logic, validate credentials
        // Return generated JWT token
        return jwtUtil.generateToken(loginRequest.getUsername());
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest registerRequest) {
        // Implement registration logic, save user to database
        return "Registration successful";
    }
}
