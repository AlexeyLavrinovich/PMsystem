package com.PMsystem.repository;

import com.PMsystem.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepo extends JpaRepository<TaskEntity, Long> {
    List<TaskEntity> findByProjectTasks(Long projectId);
}
