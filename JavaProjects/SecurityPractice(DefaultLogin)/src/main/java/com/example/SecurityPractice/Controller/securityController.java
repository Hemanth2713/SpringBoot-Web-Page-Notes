package com.example.SecurityPractice.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RequestMapping("")
@RestController
public class securityController {

    @GetMapping("/hello")
    public String helloWorld(){
        return "Hello World";
    }
}
