package com.dl.fitness_tracking_app.service;

import com.dl.fitness_tracking_app.dto.ProductRequest;
import com.dl.fitness_tracking_app.dto.UserResponse;
import com.dl.fitness_tracking_app.entity.Product;
import com.dl.fitness_tracking_app.repository.ProductRepository;
import com.dl.fitness_tracking_app.repository.UserRespository;
import com.dl.fitness_tracking_app.util.Convertor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final Convertor convertor;
    private final UserRespository userRespository;
    private final ProductRepository productRepository;
    public List<UserResponse> getAllUserInfo() {
        var users = userRespository.findAll();
        return convertor.userMapper(users);
    }

    public ResponseEntity<?> addProduct(ProductRequest productRequest) {
        var product = productRepository.findByCode(productRequest.code());
        if (product.isPresent()){
            throw new IllegalStateException("Product Already Exists!!");
        }
        var newProduct = Product.builder()
                .description(productRequest.description())
                .name(productRequest.name())
                .calories(productRequest.calories())
                .protein(productRequest.protein())
                .fat(productRequest.fats())
                .carbs(productRequest.carbs())
                .code(productRequest.code())
                .build();
        productRepository.save(newProduct);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
