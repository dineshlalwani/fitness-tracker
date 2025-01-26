package com.dl.fitness_tracking_app.dto;

public record ProductRequest (
        String description,
        String name,
        Integer calories,
        Double protein,
        Double carbs,
        Double fats,
        Integer code
) {
}
