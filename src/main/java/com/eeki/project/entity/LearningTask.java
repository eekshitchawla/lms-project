package com.eeki.project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "learning_tasks")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LearningTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "completed")
    private Boolean completed = false;

    @Column(name = "due_date")
    private LocalDate dueDate;

    // @Column(name = "deleted", nullable = false)
    // private Boolean deleted = false;
}
