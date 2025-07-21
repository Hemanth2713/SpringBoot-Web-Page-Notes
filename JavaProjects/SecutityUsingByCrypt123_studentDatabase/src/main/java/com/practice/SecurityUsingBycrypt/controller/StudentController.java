package com.practice.SecurityUsingBycrypt.controller;

import com.practice.SecurityUsingBycrypt.model.studentSecurity;
import com.practice.SecurityUsingBycrypt.service.MyUserDetailsService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {


    @Autowired
    private MyUserDetailsService service;

    @GetMapping("csrf-token")
    public CsrfToken getcsrfToken(HttpServletRequest request){
        return (CsrfToken) request.getAttribute("_csrf");
       /* Basically, what we have done is that every time we send a request which is not a GET request,
                we have to send a CSRF token with the request, otherwise it will not work, and you will get a 401 error.
                By using this method, we are requesting a token and receiving it. */

        /* The reason we are securing this is that if you try to send a request from a malicious website or
           some third-party source, the third party will not have the CSRF token. This mechanism solves that problem.
           So we have two things here:
           1. What if you don't even allow anyone outside the same website to access your server?
           2. Essentially, what I am trying to say is: what if you simply disable cross-site access,
              only allowing one specific site to access it? */
        //server.servlet.session.cookie.same-site=strict-->This will make sure that you can only access from the same website
        /* The second part is when we talk about REST APIs, there are two types:
   1. Stateless
   2. Stateful

   In a stateful API, we maintain a session, which is why every time you log in, the subsequent requests use the same session ID.
   Every request will have the same session ID. However, if we make the API stateless, which is more common in REST applications,
   we don't need to maintain a session. In this case, we don't even need a CSRF token because, in a stateless system,
   you have to send the request with the username and password every time, eliminating the need to maintain a session.
   Therefore, we don't need CSRF protection. */

       /* Now let's change the configuration and disable the CSRF token. The question arises: how will you disable it?
   By default, everything we've done so far is using the default configuration provided by Spring.
   The only things we have changed are the username and password; apart from that, everything else remains the same.
   Spring provides this default configuration.
   Now, we don't want to use the default configuration; we want to have our own custom configuration.
   To achieve this, we need to create a class for the configuration. */


    }

    @GetMapping("students")
    public List<studentSecurity> getStudents(){
        return service.geAllStudents();
    }

    @GetMapping("student/{id}")
    public studentSecurity getStudentDetails(@PathVariable("id") Long id) {
        return service.getStudent(id);  // Return the student from service
    }


    @PostMapping("student")
    public void   addStudentDetails(@RequestBody studentSecurity student){
        service.addStudent(student);
    }
}
