package com.PMsystem.controller;

import com.PMsystem.entity.ProjectEntity;
import com.PMsystem.entity.TaskEntity;
import com.PMsystem.exception.NotFoundException;
import com.PMsystem.service.ProjectService;
import com.PMsystem.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/users/get/{userId}/projects/{projectId}/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;
    @Autowired
    private ProjectService projectService;

    @GetMapping()
    public String showProjectTasks(
            @PathVariable Long userId,
            @PathVariable Long projectId,
            Model model
    ) throws NotFoundException {
        model.addAttribute("userId", userId);
        model.addAttribute("project", projectService.getOneProject(projectId));
        return "project";
    }

    @GetMapping("/new")
    public String addTaskForm(
            @PathVariable Long userId,
            @PathVariable Long projectId,
            Model model
    ) throws NotFoundException {
        TaskEntity task = new TaskEntity();
        model.addAttribute("userId", userId);
        model.addAttribute("projectId", projectId);
        model.addAttribute("task", task);
        return "add-task";
    }

    @PostMapping("/new")
    public String addTask(
            @PathVariable Long userId,
            @PathVariable Long projectId,
            @ModelAttribute TaskEntity task,
            BindingResult result
    ) throws NotFoundException {
        if (result.hasErrors()) {
            return "add-task";
        }
        taskService.addTask(projectId, task);
        return "redirect:/users/get/" + userId + "/projects/" + projectId + "/tasks";
    }

    @PutMapping("/{id}")
    public ResponseEntity updateTask(
            @PathVariable Long projectId,
            @PathVariable Long id,
            @RequestBody TaskEntity task
    ) throws NotFoundException {
        taskService.updateTask(projectId, id, task);
        return ResponseEntity.ok("Task was successfully update!");
    }

    @GetMapping("/{id}")
    public String deleteTask(
            @PathVariable Long userId,
            @PathVariable Long projectId,
            @PathVariable Long id
    ) throws NotFoundException {
        taskService.deleteTask(projectId, id);
        return "redirect:/users/get/" + userId + "/projects/" + projectId + "/tasks";
    }
}
