package com.dl.fitness_tracking_app.dto;

public record AuthenticationRequest(
        String email,
        String password
) {
}
