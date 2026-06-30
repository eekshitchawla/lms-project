package com.eeki.project.controller;

import com.eeki.project.dto.CreateTaskRequest;
import com.eeki.project.dto.LearningTaskDTO;
import com.eeki.project.dto.UpdateTaskRequest;
import com.eeki.project.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/api/v1/tasks", "/api/tasks"})
@CrossOrigin(origins = "*", maxAge = 3600)
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<List<LearningTaskDTO>> getAllTasks() {
        List<LearningTaskDTO> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<LearningTaskDTO>> getTasksByUserId(@PathVariable Long userId) {
        List<LearningTaskDTO> tasks = taskService.getTasksByUserId(userId);
        return ResponseEntity.ok(tasks);
    }

    @PostMapping
    public ResponseEntity<LearningTaskDTO> createTask(@Valid @RequestBody CreateTaskRequest request) {
        return taskService.createTask(request)
                .map(task -> ResponseEntity.status(HttpStatus.CREATED).body(task))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<LearningTaskDTO> updateTask(
            @PathVariable Long id,
            @RequestBody UpdateTaskRequest request) {
        return taskService.updateTask(id, request)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/toggle")
    public ResponseEntity<LearningTaskDTO> toggleTaskStatus(@PathVariable Long id) {
        return taskService.toggleTaskStatus(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        return taskService.deleteTask(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
