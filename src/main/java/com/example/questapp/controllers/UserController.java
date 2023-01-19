package com.example.questapp.controllers;

import com.example.questapp.entities.User;
import com.example.questapp.repos.UserRepository;
import com.example.questapp.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/users")
public class UserController {


    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    };

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    public User createUser(@RequestBody User newUser) {
        return userService.saveUser(newUser);
    }

    @GetMapping("/{userId}")
    public User getUser(@PathVariable Long userId) {
        //custom exception
        return userService.findByUserId(userId);
    }

    @PutMapping("/{userId}")
    public User updateuser(@PathVariable Long userId, @RequestBody User newUser) {
        return userService.updateUser(userId, newUser);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteById(userId);
    }







}
