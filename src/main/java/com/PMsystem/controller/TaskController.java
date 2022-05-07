package com.PMsystem.controller;

import com.PMsystem.repository.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskRepo taskRepo;

    @GetMapping
    private ResponseEntity showProjectTasks(){
        try {
            return ResponseEntity.ok(taskRepo.findAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Something bad happened...");
        }
    }
}
