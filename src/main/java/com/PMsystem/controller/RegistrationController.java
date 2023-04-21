package com.PMsystem.controller;

import com.PMsystem.entity.UserEntity;
import com.PMsystem.exception.AlreadyExistsException;
import com.PMsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity info() {
        return ResponseEntity.ok("Set your name and password");
    }

    @PostMapping
    public ResponseEntity registration(@RequestBody UserEntity user) throws AlreadyExistsException {
        userService.addUser(user);
        return ResponseEntity.ok("User was successfully add!");
    }

}
