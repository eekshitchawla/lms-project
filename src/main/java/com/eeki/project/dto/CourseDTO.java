package com.eeki.project.dto;

public record CourseDTO(
        Long id,
        String title,
        String category,
        String difficulty,
        Integer estimatedHours
) {}
