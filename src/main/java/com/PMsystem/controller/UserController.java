package com.PMsystem.controller;

import com.PMsystem.exception.AlreadyExistsException;
import com.PMsystem.exception.NotFoundException;
import com.PMsystem.service.UserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity showUsers() {
        return ResponseEntity.ok(userService.loadUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity showOne(@PathVariable Long id) throws NotFoundException {
        return ResponseEntity.ok(userService.loadUserById(id));
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
