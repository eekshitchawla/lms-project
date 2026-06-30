package com.eeki.project.dto;

import java.time.LocalDate;

public record LearningTaskDTO(
        Long id,
        String title,
        String description,
        Boolean completed,
        LocalDate dueDate,
        Long userId,
        String userFullName
) {}
