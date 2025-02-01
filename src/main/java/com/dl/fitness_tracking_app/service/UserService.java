package com.dl.fitness_tracking_app.service;

import com.dl.fitness_tracking_app.dto.ProductResponse;
import com.dl.fitness_tracking_app.dto.UserCredentials;
import com.dl.fitness_tracking_app.dto.UserResponse;
import com.dl.fitness_tracking_app.entity.User;
import com.dl.fitness_tracking_app.repository.ProductRepository;
import com.dl.fitness_tracking_app.repository.UserRepository;
import com.dl.fitness_tracking_app.util.Convertor;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserService {
    private final Convertor convertor;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    public UserResponse getUserInfo(Authentication authentication) {
        var user = (User) authentication.getPrincipal();
        var retrievedUser = userRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));

        return convertor.userToResponseDto(retrievedUser);
    }

    public UserCredentials getUserCredentials(Authentication authentication) {
        var user = (User) authentication.getPrincipal();
        var retrievedUser = userRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));

        return convertor.userCredentialsToDto(retrievedUser);
    }

    public List<ProductResponse> userProducts(Authentication authentication) {
        var user = (User) authentication.getPrincipal();
        var retrievedUser = userRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
        return convertor.productMapper(retrievedUser.getProducts());
    }

    public ResponseEntity<?> addUserProduct(Authentication authentication, Integer code) {
        var user = (User) authentication.getPrincipal();
        var retrievedUser = userRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
        var product = productRepository.findByCode(code)
                .orElseThrow(() -> new NoSuchElementException("Product does not exists"));
        retrievedUser.setProducts(List.of(product));
        userRepository.save(retrievedUser);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
