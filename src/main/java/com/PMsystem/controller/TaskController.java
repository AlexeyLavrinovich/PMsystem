package com.PMsystem.controller;

import com.PMsystem.entity.TaskEntity;
import com.PMsystem.exception.NotFoundException;
import com.PMsystem.repository.TaskRepo;
import com.PMsystem.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.quartz.QuartzTransactionManager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/projects/{projectId}")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping("/tasks")
    private ResponseEntity showProjectTasks(
            @PathVariable Long projectId,
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<Integer> size,
            @RequestParam Optional<String> sortBy
    ) {
        try {
            return ResponseEntity.ok(taskService.projectTasks(projectId, page, size, sortBy));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Something bad happened...");
        }
    }

    @PostMapping("/add")
    private ResponseEntity addTask(
            @PathVariable Long projectId,
            @RequestBody TaskEntity task
            ) {
        try {
            taskService.addTask(projectId, task);
            return ResponseEntity.ok("Task was successfully create!");
        } catch (NotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Something bad happened...");
        }
    }

    @PutMapping("/update")
    private ResponseEntity updateTask(
            @PathVariable Long projectId,
            @RequestParam Long id,
            @RequestBody TaskEntity task
    ) {
        try {
            taskService.updateTask(projectId, id, task);
            return ResponseEntity.ok("Task was successfully update!");
        } catch (NotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Something bad happened...");
        }
    }

    @DeleteMapping("/delete")
    private ResponseEntity deleteTask(
            @PathVariable Long projectId,
            @RequestParam Long id
    ) {
        try {
            taskService.deleteTask(projectId, id);
            return ResponseEntity.ok("Task was successfully delete!");
        } catch (NotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Something bad happened...");
        }
    }
}
