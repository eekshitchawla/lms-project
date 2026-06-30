package com.eeki.project.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record CreateTaskRequest(
        @NotBlank(message = "Task title is required and cannot be blank")
        String title,
        
        String description,
        
        @NotNull(message = "User ID is required")
        Long userId,
        
        LocalDate dueDate
) {}
