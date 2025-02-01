package com.dl.fitness_tracking_app.controller;

import com.dl.fitness_tracking_app.dto.ProductResponse;
import com.dl.fitness_tracking_app.dto.UserCredentials;
import com.dl.fitness_tracking_app.dto.UserResponse;
import com.dl.fitness_tracking_app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/secured/users")
public class UserController {
    private final UserService userService;
    @GetMapping
    public UserResponse getUserInfo(Authentication authentication){
        return userService.getUserInfo(authentication);
    }

    @GetMapping("/credentials")
    public UserCredentials getUserCredentials(Authentication authentication){
        return userService.getUserCredentials(authentication);
    }
    @GetMapping("/products")
    public List<ProductResponse> getUserProducts(Authentication authentication){
        return userService.userProducts(authentication);
    }
    @PostMapping("/products/{code}")
    public ResponseEntity<?> addProduct(@PathVariable("code") Integer code, Authentication authentication){
        return userService.addUserProduct(authentication,code);
    }
}
