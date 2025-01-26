package com.dl.fitness_tracking_app.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.util.List;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Document
public class User {

    @Id
    private String id;
    private String firstname;
    private String lastname;
    private LocalDate dateOfBirth;
    @Indexed(unique = true)
    private String email;
    private String password;
    private List<Workout> workouts;
    private List<Product> products;
    @Field(targetType = FieldType.STRING)
    private Role role;

}
