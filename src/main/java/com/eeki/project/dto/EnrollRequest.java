package com.eeki.project.dto;

import jakarta.validation.constraints.NotNull;

public record EnrollRequest(
        @NotNull(message = "User ID is required")
        Long userId
) {}
