package com.dl.fitness_tracking_app.repository;

import com.dl.fitness_tracking_app.entity.Workout;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface WorkoutRepository extends MongoRepository<Workout, String> {

    Optional<?> findByworkoutDate(LocalDate localDate);
}
