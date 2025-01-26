package com.dl.fitness_tracking_app.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Document
public class PerformanceMetrics {

    @Id
    private String id;
    private Double duration;
    private Integer caloriesBurned;
    private Integer intensity;

}