package com.PMsystem.controller;

import com.PMsystem.entity.UserEntity;
import com.PMsystem.repository.UserRepo;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/main")
public class MainController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping
    private List<UserEntity> showUsers(){
        return userRepo.findAll();
    }

//    @GetMapping("{id}")
//    private UserEntity showOne(@PathVariable String id){
//        return userRepo.findById(id);
//    }

    @PostMapping
    private List<UserEntity> addUser(){
        return userRepo.findAll();
    }
}
