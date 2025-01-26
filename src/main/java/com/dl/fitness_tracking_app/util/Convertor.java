package com.dl.fitness_tracking_app.util;

import com.dl.fitness_tracking_app.dto.PerformanceMetricsResponse;
import com.dl.fitness_tracking_app.dto.ProductResponse;
import com.dl.fitness_tracking_app.dto.UserResponse;
import com.dl.fitness_tracking_app.dto.WorkoutResponse;
import com.dl.fitness_tracking_app.entity.PerformanceMetrics;
import com.dl.fitness_tracking_app.entity.Product;
import com.dl.fitness_tracking_app.entity.User;
import com.dl.fitness_tracking_app.entity.Workout;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Collections;
import java.util.List;

import static com.dl.fitness_tracking_app.entity.Role.USER;

@Service
public class Convertor {
    public List<UserResponse> userMapper(List<User> users) {
        return users.stream()
                .filter(user -> user.getRole() == USER)
                .map(this::userToResponseDto)
                .toList();
    }
    public UserResponse userToResponseDto(User user) {
        return new UserResponse(
                user.getFirstname(),
                user.getLastname(),
                user.getEmail(),
                workoutMapper(user.getWorkouts()),
                productMapper(user.getProducts())
        );
    }

    public List<WorkoutResponse> workoutMapper(List<Workout> workouts) {
        return workouts.stream()
                .map(this::workoutToResponseDto)
                .toList();
    }
    public List<ProductResponse> productMapper(List<Product> products) {

        if (products == null) {
            return Collections.emptyList();
        }
        return products.stream()
                .map(this::productToResponseDto)
                .toList();
    }

    public WorkoutResponse workoutToResponseDto(Workout workout) {
        return new WorkoutResponse(
                workout.getId(),
                workout.getWorkoutDate(),
                workout.getExerciseType(),
                metricsToResponseDto(workout.getPerformanceMetrics())
        );
    }



    public PerformanceMetricsResponse metricsToResponseDto(PerformanceMetrics metrics) {
        return new PerformanceMetricsResponse(
                metrics.getDuration(),
                metrics.getCaloriesBurned(),
                metrics.getIntensity()
        );
    }

    public ProductResponse productToResponseDto(Product product) {
        return new ProductResponse(
                product.getDescription(),
                product.getName(),
                product.getCalories(),
                product.getProtein(),
                product.getCarbs(),
                product.getFat(),
                product.getCode(),
                Base64.getEncoder().encodeToString(product.getImage())
        );
    }


}
