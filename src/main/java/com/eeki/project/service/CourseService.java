package com.eeki.project.service;

import com.eeki.project.dto.CourseDTO;
import com.eeki.project.dto.CreateTaskRequest;
import com.eeki.project.dto.EnrollRequest;
import com.eeki.project.entity.Course;
import com.eeki.project.repository.CourseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final TaskService taskService;

    public CourseService(CourseRepository courseRepository, TaskService taskService) {
        this.courseRepository = courseRepository;
        this.taskService = taskService;
    }

    public List<CourseDTO> getAllCourses() {
        return courseRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    public Optional<CourseDTO> getCourseById(Long id) {
        return courseRepository.findById(id)
                .map(this::mapToDTO);
    }

    public List<CourseDTO> getCoursesByCategory(String category) {
        return courseRepository.findByCategory(category)
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    @Transactional
    public void enrollUser(Long courseId, EnrollRequest enrollRequest) {
        // Fetch course details to verify it exists
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + courseId));

        // Create task request to enroll user
        CreateTaskRequest taskRequest = new CreateTaskRequest(
                "Complete: " + course.getTitle(),
                "Enrolled in " + course.getCategory(),
                enrollRequest.userId(),
                null
        );

        // Create the task for the user
        taskService.createTask(taskRequest)
                .orElseThrow(() -> new RuntimeException("Enrollment failed. User not found."));
    }

    private CourseDTO mapToDTO(Course course) {
        return new CourseDTO(
                course.getId(),
                course.getTitle(),
                course.getCategory(),
                course.getDifficulty(),
                course.getEstimatedHours()
        );
    }
}
