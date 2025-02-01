package com.dl.fitness_tracking_app.repository;

import com.dl.fitness_tracking_app.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends MongoRepository<Product,String> {
    Optional<Product> findByCode(Integer code);

}
