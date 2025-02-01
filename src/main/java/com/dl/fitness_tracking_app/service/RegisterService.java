package com.dl.fitness_tracking_app.service;

import com.dl.fitness_tracking_app.dto.RegisterRequest;
import com.dl.fitness_tracking_app.entity.Role;
import com.dl.fitness_tracking_app.entity.User;
import com.dl.fitness_tracking_app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public ResponseEntity<?> registerUser(RegisterRequest registerRequest) {
        if(userRepository.existsByEmail(registerRequest.email())){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        var user = User.builder()
                .firstname(registerRequest.firstName())
                .lastname(registerRequest.lastName())
                .email(registerRequest.email())
                .password(passwordEncoder.encode(registerRequest.password())) //todo encode password using spring security
                .dateOfBirth(registerRequest.dateOfBirth())
                .role(Role.USER)
                .build();

        userRepository.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
