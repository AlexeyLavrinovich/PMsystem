package com.PMsystem.controller;

import com.PMsystem.exception.NotFoundException;
import com.PMsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    private ResponseEntity showUsers(){
        try{
            return ResponseEntity.ok(userService.loadUsers());
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Something bad happened...");
        }
    }

    @GetMapping("/{id}")
    private ResponseEntity showOne(@PathVariable Long id){
        try{
            return ResponseEntity.ok(userService.loadUserById(id));
        } catch (NotFoundException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Something bad happened...");
        }
    }

    @PutMapping("/{id}")
    private ResponseEntity changeRole(@PathVariable Long id){
        try{
            userService.addRole(id);
            return ResponseEntity.ok("User was successfully update!");
        } catch (NotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Something bad happened...");
        }
    }

    @DeleteMapping("/{id}")
    private ResponseEntity deleteUser(@PathVariable Long id){
        try{
            userService.deleteUser(id);
            return ResponseEntity.ok("User was successfully delete!");
        } catch (NotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Something bad happened...");
        }
    }
}
