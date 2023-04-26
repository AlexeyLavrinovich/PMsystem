package com.PMsystem.controller;

import com.PMsystem.exception.AlreadyExistsException;
import com.PMsystem.exception.NotFoundException;
import com.PMsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String showUsers(Model model) {
        model.addAttribute("users", userService.loadUsers());
        return "all-users";
    }

    @GetMapping("/get/{id}")
    public String showOne(@PathVariable Long id, Model model) throws NotFoundException {
        model.addAttribute("user", userService.loadUserById(id));
        return "user";
    }

    @PutMapping("/{id}")
    public ResponseEntity changeRole(@PathVariable Long id) throws AlreadyExistsException, NotFoundException {
        userService.addRole(id);
        return ResponseEntity.ok("User was successfully update!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) throws NotFoundException {
        userService.deleteUser(id);
        return ResponseEntity.ok("User was successfully delete!");
    }
}
