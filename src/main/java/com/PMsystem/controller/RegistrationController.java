package com.PMsystem.controller;

import com.PMsystem.entity.ProjectEntity;
import com.PMsystem.entity.UserEntity;
import com.PMsystem.exception.AlreadyExistsException;
import com.PMsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users/registration")
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String info(Model model) {
        UserEntity user = new UserEntity();
        model.addAttribute("user", user);
        return "registration";
    }

    @PostMapping
    public String registration(
            @ModelAttribute UserEntity user,
            BindingResult result
    ) throws AlreadyExistsException {
        if (result.hasErrors()) {
            return "registration";
        }
        userService.addUser(user);
        return "redirect:/users";
    }

}
