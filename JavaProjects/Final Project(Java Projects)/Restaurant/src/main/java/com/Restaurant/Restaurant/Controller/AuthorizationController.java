package com.Restaurant.Restaurant.Controller;

import com.Restaurant.Restaurant.DTO.LoginDto;
import com.Restaurant.Restaurant.DTO.UserRequestDto;
import com.Restaurant.Restaurant.Model.Role;
import com.Restaurant.Restaurant.Service.AuthorizationService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("api/user")
public class    AuthorizationController {

    @Autowired
    private AuthorizationService authorizationService;

    @PostMapping("/register")
    private ResponseEntity<String> registerUser(@Valid @RequestBody UserRequestDto userRequestDto) {
        log.info("Received registration request for USER with email: {}", userRequestDto.getEmail());

        String registrationStatus = authorizationService.registerUser(userRequestDto, Role.USER);
        log.info("User registered successfully with email: {}", userRequestDto.getEmail());

        return new ResponseEntity<>("User Registered Successfully with mail " + userRequestDto.getEmail(), HttpStatus.CREATED);
    }

    @PostMapping("/admin/register")
    private ResponseEntity<String> registerAdmin(@Valid @RequestBody UserRequestDto userRequestDto) {
        log.info("Received registration request for ADMIN with email: {}", userRequestDto.getEmail());

        String registrationStatus = authorizationService.registerUser(userRequestDto, Role.ADMIN);
        log.info("Admin registered successfully with email: {}", userRequestDto.getEmail());

        return new ResponseEntity<>("Admin Registered Successfully with mail " + userRequestDto.getEmail(), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    private  ResponseEntity<Map<String,String>> loginUser(@Valid @RequestBody LoginDto loginDto){
        log.info("Login Attempt for user : {} ",loginDto.getEmail());
        Map<String,String> loginResponse=authorizationService.loginUser(loginDto);
        if (loginResponse.containsKey("token")) {
            log.info("Login successful for username: {}", loginDto.getEmail());
        } else {
            log.warn("Login failed for username: {}", loginDto.getEmail());
        }
        return new ResponseEntity<>(loginResponse,HttpStatus.OK);
    }
}
