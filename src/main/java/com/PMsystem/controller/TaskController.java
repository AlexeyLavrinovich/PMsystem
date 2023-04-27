package com.PMsystem.controller;

import com.PMsystem.entity.TaskEntity;
import com.PMsystem.exception.NotFoundException;
import com.PMsystem.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/projects/{projectId}")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping("/tasks")
    public ResponseEntity showProjectTasks(
            @PathVariable Long projectId,
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<Integer> size,
            @RequestParam Optional<String> sortBy
    ) {
        return ResponseEntity.ok(taskService.projectTasks(projectId, page, size, sortBy));
    }

    @PostMapping("/add")
    public ResponseEntity addTask(
            @PathVariable Long projectId,
            @RequestBody TaskEntity task
    ) throws NotFoundException {
        taskService.addTask(projectId, task);
        return ResponseEntity.ok("Task was successfully create!");
    }

    @PutMapping("/update")
    public ResponseEntity updateTask(
            @PathVariable Long projectId,
            @RequestParam Long id,
            @RequestBody TaskEntity task
    ) throws NotFoundException {
        taskService.updateTask(projectId, id, task);
        return ResponseEntity.ok("Task was successfully update!");
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteTask(
            @PathVariable Long projectId,
            @RequestParam Long id
    ) throws NotFoundException {
        taskService.deleteTask(projectId, id);
        return ResponseEntity.ok("Task was successfully delete!");
    }
}
