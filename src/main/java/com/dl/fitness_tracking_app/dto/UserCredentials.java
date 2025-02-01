package com.dl.fitness_tracking_app.dto;

import java.time.LocalDate;

public record UserCredentials(
        String firstName,
        String lastName,
        String email,
        LocalDate dateOfBirth
) {
}
