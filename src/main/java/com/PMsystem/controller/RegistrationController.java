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
    private ResponseEntity info() {
        try{
            return ResponseEntity.ok("Set your name and password");
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Something bad happened...");
        }
    }

    @PostMapping
    private ResponseEntity registration(@RequestBody UserEntity user){
        try{
            userService.addUser(user);
            return ResponseEntity.ok("User was successfully add!");
        } catch (AlreadyExistsException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Something bad happened...");
        }
    }

}
