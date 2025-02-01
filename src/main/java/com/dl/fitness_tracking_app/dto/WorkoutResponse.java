package com.dl.fitness_tracking_app.dto;

import java.time.LocalDate;

public record WorkoutResponse (
        String id,
        LocalDate workoutDate,
        String exerciseType,
        PerformanceMetricsResponse performanceMetricsResponse) {

}
