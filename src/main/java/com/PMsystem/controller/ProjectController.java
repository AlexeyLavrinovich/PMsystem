package com.PMsystem.controller;

import com.PMsystem.entity.ProjectEntity;
import com.PMsystem.exception.AlreadyExistsException;
import com.PMsystem.exception.NotFoundException;
import com.PMsystem.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/users/get/{userId}/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping
    public String getProjects(
            @PathVariable Long userId,
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<Integer> size,
            @RequestParam Optional<String> sortBy,
            Model model
    ) {
        model.addAttribute("projects", projectService.getProjects(userId, page, size, sortBy));
        return "all-projects";
    }

    @GetMapping("/{id}")
    public String getOneProject(
            @PathVariable Long id,
            Model model
    ) throws NotFoundException {
        model.addAttribute("project", projectService.getOneProject(id));
        return "project";
    }

    @GetMapping("/create")
    public String showCreateProjectForm(
            @PathVariable Long userId,
            Model model
    ) {
        ProjectEntity project = new ProjectEntity();
        model.addAttribute("userId", userId);
        model.addAttribute("project", project);
        return "add-project";
    }

    @PostMapping("/create")
    public String createProject(
            @PathVariable Long userId,
            @ModelAttribute ProjectEntity project,
            BindingResult result
    ) throws AlreadyExistsException, NotFoundException {
        if (result.hasErrors()) {
            return "add-project";
        }
        projectService.addProject(project, userId);
        return "redirect:/users/get/" + userId;
    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity renameProject(
//            @RequestParam Long userId,
//            @PathVariable Long id,
//            @RequestBody ProjectEntity project
//    ) throws AlreadyExistsException, NotFoundException {
//        projectService.renameProject(userId, id, project);
//        return ResponseEntity.ok("Project was successfully renamed!");
//    }

    @GetMapping("/delete/{id}")
    public String deleteProject(
            @PathVariable Long id
    ) throws NotFoundException {
        Long userId = projectService.findProjectById(id).getUser().getId();
        projectService.deleteProject(id);
        return "redirect:/users/get/" + userId;
    }
}
