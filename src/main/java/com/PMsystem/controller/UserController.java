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
        model.addAttribute("userId", id);
        model.addAttribute("user", userService.loadUserById(id));
        return "user";
    }

    @GetMapping("/upgrade/{id}")
    public String changeRole(@PathVariable Long id) throws AlreadyExistsException, NotFoundException {
        userService.addRole(id);
        return "redirect:/users";
    }
//
//    @GetMapping("/delete/{id}")
//    public String deleteUser(@PathVariable Long id) throws NotFoundException {
//        userService.deleteUser(id);
//        return "redirect:/users";
//    }
}
