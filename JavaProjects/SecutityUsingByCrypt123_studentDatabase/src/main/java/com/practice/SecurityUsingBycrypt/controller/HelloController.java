package com.practice.SecurityUsingBycrypt.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class HelloController {
    @GetMapping("hello")
    public  String greet(){
        return  "Hello World";
    }

    @GetMapping("about")
    public String about(HttpServletRequest request){//Here we are using HttpServletRequest because it has a method called session.
        return "Hello World "+request.getSession().getId();
    }
}
