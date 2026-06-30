package com.eeki.project.controller;

import com.eeki.project.dto.CourseDTO;
import com.eeki.project.dto.EnrollRequest;
import com.eeki.project.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/courses")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<List<CourseDTO>> getAllCourses() {
        List<CourseDTO> courses = courseService.getAllCourses();
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable Long id) {
        return courseService.getCourseById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<CourseDTO>> getCoursesByCategory(@PathVariable String category) {
        List<CourseDTO> courses = courseService.getCoursesByCategory(category);
        return ResponseEntity.ok(courses);
    }

    @PostMapping("/{courseId}/enroll")
    public ResponseEntity<String> enrollUser(
            @PathVariable Long courseId,
            @Valid @RequestBody EnrollRequest request) {
        try {
            courseService.enrollUser(courseId, request);
            return ResponseEntity.status(HttpStatus.CREATED).body("Successfully enrolled in course");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
