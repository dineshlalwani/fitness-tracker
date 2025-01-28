package com.dl.fitness_tracking_app.repository;

import com.dl.fitness_tracking_app.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRespository extends MongoRepository<User,String> {

    boolean existsByEmail(String email);
}
