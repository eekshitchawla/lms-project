package com.eeki.project.repository;

import com.eeki.project.entity.LearningTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LearningTaskRepository extends JpaRepository<LearningTask, Long> {
    List<LearningTask> findByUserId(Long userId);
    List<LearningTask> findByUserIdAndCompleted(Long userId, Boolean completed);
}
