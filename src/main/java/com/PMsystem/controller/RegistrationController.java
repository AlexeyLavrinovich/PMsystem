package com.PMsystem.controller;

import com.PMsystem.entity.UserEntity;
import com.PMsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
    @Autowired
    private UserService userService;

    @GetMapping
    public String registration(){
        return "registration";
    }

    @PostMapping
    public String addUser(UserEntity user, Model model){
        if(userService.addUser(user)){
            return "redirect:/login";
        }
        model.addAttribute("error", "User exists!");
        return "registration";
    }
}
