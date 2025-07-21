package com.practice.Secutity.controller;


import com.practice.Secutity.model.User;
import com.practice.Secutity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService service;
    @PostMapping("register")
    public User register(@RequestBody User user){
        return  service.saveUser(user);
    }
}
