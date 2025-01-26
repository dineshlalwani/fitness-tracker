package com.dl.fitness_tracking_app.dto;

public record PerformanceMetricsResponse(
        Double duration,
        Integer caloriesBurned,
        Integer intensity) {
}
