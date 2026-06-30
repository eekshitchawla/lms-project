package com.eeki.project.service;

import com.eeki.project.dto.CreateTaskRequest;
import com.eeki.project.dto.LearningTaskDTO;
import com.eeki.project.dto.UpdateTaskRequest;
import com.eeki.project.entity.LearningTask;
import com.eeki.project.entity.User;
import com.eeki.project.repository.LearningTaskRepository;
import com.eeki.project.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private static final Logger logger = LoggerFactory.getLogger(TaskService.class);
    private final LearningTaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskService(LearningTaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public List<LearningTaskDTO> getAllTasks() {
        return taskRepository.findAll()
            .stream()
            .map(this::mapToDTO)
            .toList();
    }

    public List<LearningTaskDTO> getTasksByUserId(Long userId) {
        return taskRepository.findByUserId(userId)
            .stream()
            .map(this::mapToDTO)
            .toList();
    }

    public Optional<LearningTaskDTO> createTask(CreateTaskRequest request) {
        return userRepository.findById(request.userId())
                .map(user -> {
                    LearningTask task = new LearningTask();
                    task.setUser(user);
                    task.setTitle(request.title());
                    task.setDescription(request.description());
                    task.setDueDate(request.dueDate());
                    task.setCompleted(false);
                    // task.setDeleted(false);

                    LearningTask savedTask = taskRepository.save(task);

                    logger.info("Task created successfully. TaskId: {}, UserId: {}, Title: {}", 
                            savedTask.getId(), user.getId(), savedTask.getTitle());

                    return mapToDTO(savedTask);
                });
    }

    public Optional<LearningTaskDTO> updateTask(Long id, UpdateTaskRequest request) {
        return taskRepository.findById(id)
                .map(task -> {
                    task.setTitle(request.title());
                    task.setDescription(request.description());

                    LearningTask updatedTask = taskRepository.save(task);
                    return mapToDTO(updatedTask);
                });
    }

    public Optional<LearningTaskDTO> toggleTaskStatus(Long id) {
        Optional<LearningTask> taskOptional = taskRepository.findById(id);

        if (taskOptional.isEmpty()) {
            logger.warn("Attempted to toggle non-existent task. TaskId: {}", id);
            return Optional.empty();
        }

        LearningTask task = taskOptional.get();
        task.setCompleted(!task.getCompleted());
        LearningTask updatedTask = taskRepository.save(task);

        return Optional.of(mapToDTO(updatedTask));
    }

    public boolean deleteTask(Long id) {
        if (taskRepository.existsById(id)) {
            taskRepository.deleteById(id);
            logger.info("Task deleted. TaskId: {}", id);
            return true;
        }
        return false;
    }

    private LearningTaskDTO mapToDTO(LearningTask task) {
        return new LearningTaskDTO(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getCompleted(),
                task.getDueDate(),
                task.getUser().getId(),
                task.getUser().getFullName()
        );
    }
}
