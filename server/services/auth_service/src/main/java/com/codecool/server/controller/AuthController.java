package com.codecool.server.controller;


import com.codecool.server.DTO.AuthResponseDTO;
import com.codecool.server.DTO.CredentialsDTO;
import com.codecool.server.model.RegisterRequest;
import com.codecool.server.model.UserCheckRequest;
import com.codecool.server.security.JWTUtil;
import com.codecool.server.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private JWTUtil jwtUtil;
    private AuthService authService;
    private final AuthenticationConfiguration authenticationConfiguration;


    @Autowired
    public AuthController(JWTUtil jwtUtil, AuthService authService, AuthenticationConfiguration authenticationConfiguration) {
        this.jwtUtil = jwtUtil;
        this.authService = authService;
this.authenticationConfiguration = authenticationConfiguration;    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody CredentialsDTO credentials) throws Exception {

        AuthenticationManager authenticationManager = authenticationConfiguration.getAuthenticationManager();
        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                credentials.email(),
                                credentials.password()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtUtil.generateJwtToken(authentication);


        return new ResponseEntity<>(new AuthResponseDTO(token, "User login successfully"), HttpStatus.OK);
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

//    @PostMapping("/sendMessage")
//    public String sendMessage(@RequestBody UserMessage userMessage) {
//         authService.sendMessage(userMessage);
//         return "Message sent to user-service";
//    }
//}
