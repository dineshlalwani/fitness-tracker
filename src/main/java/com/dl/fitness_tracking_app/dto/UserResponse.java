package com.dl.fitness_tracking_app.dto;

import java.util.List;

public record UserResponse(
        String firstName,
        String lastName,
        String email,
        List<WorkoutResponse> workouts,
        List<ProductResponse> products) {

}
