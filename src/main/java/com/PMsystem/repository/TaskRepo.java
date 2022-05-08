package com.PMsystem.repository;

import com.PMsystem.entity.TaskEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepo extends JpaRepository<TaskEntity, Long> {
    Page<TaskEntity> findByProjectId(Long projectId, Pageable pageable);
}
