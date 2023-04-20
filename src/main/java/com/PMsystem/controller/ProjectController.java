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
    private ResponseEntity getProjects(
            @RequestParam Long userId,
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<Integer> size,
            @RequestParam Optional<String> sortBy
    ) {
        try {
            return ResponseEntity.ok(projectService.getProjects(userId, page, size, sortBy));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Something bad happened...");
        }
    }

    @GetMapping("/{id}")
    private ResponseEntity getOneProject(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(projectService.getOneProject(id));
        } catch (NotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch (Exception e) {
            return ResponseEntity.badRequest().body("Something bad happened...");
        }
    }

    @PostMapping
    private ResponseEntity createProject(@RequestParam Long userId,@RequestBody ProjectEntity project) {
        try {
            projectService.addProject(project, userId);
            return ResponseEntity.ok("Project was successfully create!");
        } catch (NotFoundException | AlreadyExistsException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Something bad happened...");
        }
    }

    @PutMapping("/{id}")
    private ResponseEntity renameProject(@RequestParam Long userId, @PathVariable Long id, @RequestBody ProjectEntity project) {
        try {
            projectService.renameProject(userId, id, project);
            return ResponseEntity.ok("Project was successfully renamed!");
        } catch (NotFoundException | AlreadyExistsException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Something bad happened...");
        }
    }

    @DeleteMapping("/{id}")
    private ResponseEntity deleteProject(@PathVariable Long id) {
        try {
            projectService.deleteProject(id);
            return ResponseEntity.ok("Project was successfully delete!");
        } catch (NotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch (Exception e) {
            return ResponseEntity.badRequest().body("Something bad happened...");
        }
    }
}
