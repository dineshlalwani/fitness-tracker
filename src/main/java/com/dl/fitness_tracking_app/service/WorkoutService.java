package com.dl.fitness_tracking_app.service;

import com.dl.fitness_tracking_app.dto.WorkoutResponse;
import com.dl.fitness_tracking_app.entity.PerformanceMetrics;
import com.dl.fitness_tracking_app.entity.User;
import com.dl.fitness_tracking_app.entity.Workout;
import com.dl.fitness_tracking_app.repository.UserRepository;
import com.dl.fitness_tracking_app.repository.WorkoutRepository;
import com.dl.fitness_tracking_app.util.Convertor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class WorkoutService {
    private final WorkoutRepository workoutRepository;
    private final UserRepository userRepository;
    private final Convertor convertor;

    @Transactional
    public ResponseEntity<?> addWorkout(Authentication authentication, WorkoutResponse workoutResponse) {
        var user = (User) authentication.getPrincipal();
        workoutRepository.findByworkoutDate(workoutResponse.workoutDate())
                .ifPresent(e -> {
                    throw new IllegalStateException("Workout Already Exists");
                });
        var performanceMeterics = workoutResponse.performanceMetricsResponse();
        var newPerformanceMeterics = PerformanceMetrics.builder()
                .caloriesBurned(performanceMeterics.caloriesBurned())
                .duration(performanceMeterics.duration())
                .intensity(performanceMeterics.intensity())
                .build();
        var newWorkout  = Workout.builder()
                .workoutDate(workoutResponse.workoutDate())
                .exerciseType(workoutResponse.exerciseType())
                .performanceMetrics(newPerformanceMeterics)
                .build();
        workoutRepository.save(newWorkout);

        user.getWorkouts().add(newWorkout);

        userRepository.save(user);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public ResponseEntity<?> deleteWorkout(String id) {
        var user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        var workout = workoutRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Workout does not exist"));
        workoutRepository.delete(workout);

        user.getWorkouts().remove(workout);
        userRepository.save(user);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
