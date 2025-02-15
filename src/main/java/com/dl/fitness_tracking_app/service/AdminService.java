package com.dl.fitness_tracking_app.service;

import com.dl.fitness_tracking_app.dto.ProductRequest;
import com.dl.fitness_tracking_app.dto.ProductResponse;
import com.dl.fitness_tracking_app.dto.UserResponse;
import com.dl.fitness_tracking_app.entity.Product;
import com.dl.fitness_tracking_app.repository.ProductRepository;
import com.dl.fitness_tracking_app.repository.UserRepository;
import com.dl.fitness_tracking_app.util.Convertor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final Convertor convertor;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    public List<UserResponse> getAllUserInfo() {
        var users = userRepository.findAll();
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

    public List<ProductResponse> getProducts() {
        var products = productRepository.findAll();
        return convertor.productMapper(products);
    }

    public ResponseEntity<?> deleteProduct(Integer code) {
        var product = productRepository.findByCode(code)
                .orElseThrow(()->new NoSuchElementException("Product does not exist"));
        productRepository.delete(product);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
