package com.dl.fitness_tracking_app.dto;

import java.time.LocalDate;

public record RegisterRequest(
        String firstName,
        String lastName,
        String email,
        String password,
        LocalDate dateOfBirth
) {
}
