package com.eeki.project.exception;

import jakarta.persistence.EntityNotFoundException;

public class TaskNotFoundException extends EntityNotFoundException {
    
    public TaskNotFoundException(Long taskId) {
        super("Task with ID " + taskId + " not found");
    }
    
    public TaskNotFoundException(String message) {
        super(message);
    }
}
