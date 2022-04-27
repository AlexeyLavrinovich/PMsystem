package com.PMsystem.service;

import com.PMsystem.entity.ProjectEntity;
import com.PMsystem.exception.AlreadyExistsException;
import com.PMsystem.repository.ProjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepo projectRepo;

    public List<ProjectEntity> getProjects() {
        return projectRepo.findAll();
    }

    public void setProject(ProjectEntity project) throws AlreadyExistsException {
        ProjectEntity projectFromDb = projectRepo.findByName(project.getName());
        if (projectFromDb != null) {
            throw new AlreadyExistsException("Project with this name already exists!");
        }
        projectRepo.save(project);
    }
}
