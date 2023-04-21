package com.PMsystem.controller;

import com.PMsystem.entity.ProjectEntity;
import com.PMsystem.exception.AlreadyExistsException;
import com.PMsystem.exception.NotFoundException;
import com.PMsystem.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping
    public ResponseEntity getProjects(
            @RequestParam Long userId,
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<Integer> size,
            @RequestParam Optional<String> sortBy
    ) {
        return ResponseEntity.ok(projectService.getProjects(userId, page, size, sortBy));
    }

    @GetMapping("/{id}")
    public ResponseEntity getOneProject(
            @PathVariable Long id
    ) throws NotFoundException {
        return ResponseEntity.ok(projectService.getOneProject(id));
    }

    @PostMapping
    public ResponseEntity createProject(
            @RequestParam Long userId,
            @RequestBody ProjectEntity project
    ) throws AlreadyExistsException, NotFoundException {
        projectService.addProject(project, userId);
        return ResponseEntity.ok("Project was successfully create!");

    }

    @PutMapping("/{id}")
    public ResponseEntity renameProject(
            @RequestParam Long userId,
            @PathVariable Long id,
            @RequestBody ProjectEntity project
    ) throws AlreadyExistsException, NotFoundException {
        projectService.renameProject(userId, id, project);
        return ResponseEntity.ok("Project was successfully renamed!");

    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProject(
            @PathVariable Long id
    ) throws NotFoundException {
        projectService.deleteProject(id);
        return ResponseEntity.ok("Project was successfully delete!");
    }
}
