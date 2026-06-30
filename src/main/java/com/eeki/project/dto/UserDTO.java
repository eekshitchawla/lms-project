package com.eeki.project.dto;

public record UserDTO(
        Long id,
        String fullName,
        String email,
        String role
) {}
