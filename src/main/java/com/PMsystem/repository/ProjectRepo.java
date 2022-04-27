package com.PMsystem.repository;

import com.PMsystem.entity.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepo extends JpaRepository<ProjectEntity, Long> {

    ProjectEntity findByName(String name);

}
