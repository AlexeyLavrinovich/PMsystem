package com.PMsystem.service;

import com.PMsystem.entity.ProjectEntity;
import com.PMsystem.exception.AlreadyExistsException;
import com.PMsystem.exception.NotFoundException;
import com.PMsystem.model.Project;
import com.PMsystem.repository.ProjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepo projectRepo;
    @Autowired
    private UserService userService;

    public List<Project> getProjects() {
        return projectRepo.findAll().stream().map(Project::toModel).collect(Collectors.toList());
    }

    public void setProject(ProjectEntity project, Long userId) throws NotFoundException, AlreadyExistsException {
        userService.addProjectToUser(userId, project);
        ProjectEntity projectFromDb = projectRepo.findByName(project.getName());
        if (projectFromDb != null) {
            throw new AlreadyExistsException("Project with this name already exists!");
        }
        projectRepo.save(project);
    }

    public void deleteProject(Long id) throws NotFoundException {
        Optional<ProjectEntity> projectFromDb = projectRepo.findById(id);
        if (projectFromDb.isEmpty()) {
            throw new NotFoundException("Project doesn't exists!");
        }
        ProjectEntity project = projectFromDb.get();
        projectRepo.delete(project);
    }

    public void renameProject(Long id, ProjectEntity newProject) throws NotFoundException {
        Optional<ProjectEntity> projectFromDb = projectRepo.findById(id);
        if (projectFromDb.isEmpty()) {
            throw new NotFoundException("Project doesn't exists!");
        }
        ProjectEntity project = projectFromDb.get();
        project.setName(newProject.getName());
        projectRepo.save(project);
    }

    public Project getOneProject(Long id) throws NotFoundException {
        Optional<ProjectEntity> projectFromDb = projectRepo.findById(id);
        if (projectFromDb.isEmpty()) {
            throw new NotFoundException("Project doesn't exists!");
        }
        return Project.toModel(projectFromDb.get());
    }
}
