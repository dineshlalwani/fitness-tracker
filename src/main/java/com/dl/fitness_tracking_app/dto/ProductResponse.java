package com.dl.fitness_tracking_app.dto;

public record ProductResponse (
        String description,
        String name,
        Integer calories,
        Double protein,
        Double carbs,
        Double fats,
        Integer code
        //String image
){
}
