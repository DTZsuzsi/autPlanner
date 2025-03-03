package com.codecool.server.controller;


import com.codecool.server.model.RegisterRequest;
import com.codecool.server.model.UserCheckRequest;
import com.codecool.server.security.JWTUtil;
import com.codecool.server.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

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
    public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest) throws ExecutionException, InterruptedException, TimeoutException {
        UserCheckRequest userCheckRequest = new UserCheckRequest(registerRequest.getFirstName(), registerRequest.getLastName(), registerRequest.getEmail(), registerRequest.getPassword());
        authService.checkUser(userCheckRequest);
        String response = authService.getUserResponseFuture().get(10, TimeUnit.SECONDS);
        authService.resetUserResponseFuture();

        if (response.equals("User exists") || response.equals("User created")) {
            return new ResponseEntity<>("Registration was successful", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Email is taken!", HttpStatus.BAD_REQUEST);

        }
    }

    @PostMapping("/authorize")
    public String authorize(@RequestBody UserCheckRequest userCheckRequest) {
        authService.checkUser(userCheckRequest);
        return "User check request sent to user-service";
    }
}
