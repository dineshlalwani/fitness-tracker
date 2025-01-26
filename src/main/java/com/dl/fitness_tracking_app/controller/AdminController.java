package com.dl.fitness_tracking_app.controller;

import com.dl.fitness_tracking_app.dto.ProductRequest;
import com.dl.fitness_tracking_app.dto.UserResponse;
import com.dl.fitness_tracking_app.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin")
public class AdminController {
    private final AdminService adminService;

    @GetMapping("/users")
    public List<UserResponse> getAllUserInfo(){
        return adminService.getAllUserInfo();
    }

    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@RequestBody ProductRequest productRequest){
        return adminService.addProduct(productRequest);
    }
}
