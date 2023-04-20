package com.PMsystem.service;

import com.PMsystem.entity.ProjectEntity;
import com.PMsystem.entity.UserEntity;
import com.PMsystem.exception.AlreadyExistsException;
import com.PMsystem.exception.NotFoundException;
import com.PMsystem.model.Project;
import com.PMsystem.repository.ProjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepo projectRepo;
    @Autowired
    private UserService userService;

    public Page<Project> getProjects(
            Long userId,
            Optional<Integer> page,
            Optional<Integer> size,
            Optional<String> sortBy
    ) {
        return  new PageImpl<Project>(projectRepo.findAll(
                PageRequest.of(
                        page.orElse(0),
                        size.orElse(5),
                        Sort.Direction.DESC, sortBy.orElse("id")
                )
        )
                .stream().filter(projectEntity -> projectEntity.getUser().getId().equals(userId))
                .map(Project::toModel)
                .collect(Collectors.toList()));

    }

    public void addProject(ProjectEntity project, Long userId) throws NotFoundException, AlreadyExistsException {
        projectRepo.save(userService.addProjectToUser(userId, project));
    }

    public ProjectEntity findProjectById(Long id) throws NotFoundException {
        Optional<ProjectEntity> project = projectRepo.findById(id);
        if (project.isEmpty()) {
            throw new NotFoundException("Project doesn't exists!");
        }
        return project.get();
    }

    public void deleteProject(Long id) throws NotFoundException {
        projectRepo.delete(findProjectById(id));
    }

    public void renameProject(Long userId, Long id, ProjectEntity newProject) throws NotFoundException, AlreadyExistsException {
        UserEntity user = userService.findUserById(userId);
        ProjectEntity project = findProjectById(id);
        if (userService.findProjectByName(user, project).isEmpty()){
            throw new NotFoundException("Can't find project!");
        } else if (userService.findProjectByName(user, newProject).isPresent()) {
            throw new AlreadyExistsException("Project already exists!");
        }
        project.setName(newProject.getName());
        projectRepo.save(project);
    }

    public Project getOneProject(Long id) throws NotFoundException {
        return Project.toModel(findProjectById(id));
    }
}
