package com.example.CarBookingTask.Controller;

import com.example.CarBookingTask.Model.AdminDetails;
import com.example.CarBookingTask.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/admin")
@RestController
public class AdminController {
    @Autowired
    private AdminService service;


    @PostMapping("/register")
    public AdminDetails registerAdminDetails(@RequestBody AdminDetails adminDetails){
        return service.registerAdmin(adminDetails);
    }

    @GetMapping("/admins")
    public List<AdminDetails> getAdminDetails(){
        return service.getAlAdmins();
    }
}
