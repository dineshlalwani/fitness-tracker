package com.dl.fitness_tracking_app.controller;

import com.dl.fitness_tracking_app.dto.AuthenticationRequest;
import com.dl.fitness_tracking_app.dto.RegisterRequest;
import com.dl.fitness_tracking_app.service.AuthenticationService;
import com.dl.fitness_tracking_app.service.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final RegisterService registerService;
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest){
        return registerService.registerUser(registerRequest);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest authenticationRequest){
        return authenticationService.authenticate(authenticationRequest);
    }

    //todo controller to logout user
}
