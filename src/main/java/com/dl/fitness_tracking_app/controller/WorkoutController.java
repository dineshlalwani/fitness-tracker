package com.dl.fitness_tracking_app.controller;


import com.dl.fitness_tracking_app.dto.WorkoutResponse;
import com.dl.fitness_tracking_app.service.WorkoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/secured/workout")
public class WorkoutController {
    private final WorkoutService workoutService;

    @PostMapping
    public ResponseEntity<?> addWorkout(@RequestBody WorkoutResponse workoutResponse, Authentication authentication){
        return workoutService.addWorkout(authentication, workoutResponse);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteWorkout(@PathVariable("id") String id) {
        return workoutService.deleteWorkout(id);
    }
}
