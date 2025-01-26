package com.dl.fitness_tracking_app.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Document
public class Product {
    @Id
    private String id;
    private String description;
    private String name;
    private Integer calories;
    private Double protein;
    private Double carbs;
    private Double fat;
    private byte[] image;
    @Indexed(unique = true)
    private Integer code;
}
