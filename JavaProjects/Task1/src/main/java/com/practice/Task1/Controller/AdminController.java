package com.practice.Task1.Controller;

import com.practice.Task1.Model.Admin;
import com.practice.Task1.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/admin")
@RestController
public class AdminController {
    @Autowired
    private AdminService service;

    @PostMapping("register")
    public Admin registerAdmin(@RequestBody Admin admin){
        return service.saveAdmin(admin);
    }

    @GetMapping("admins")
    public List<Admin> adminsList(){
        return service.getAllAdmins();
    }

    @PutMapping("/update-password/{username}")
    public ResponseEntity<?> updatePassword(@PathVariable("username") String username, @RequestBody String newPassword) {
        // Fetch the admin by username
        Admin admin = service.getAdminByUsername(username);
        if (admin != null) {
            // Update the password
            admin.setPassword(newPassword);
            // Save the updated admin back to the database
            service.saveAdmin(admin);
            return ResponseEntity.ok(admin); // Return updated admin
        } else {
            // Return an error response if admin is not found
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Admin with username '" + username + "' not found.");
        }
    }

    // Delete admin by username
    @DeleteMapping("/delete/{username}")
    public String deleteAdminFromList(@PathVariable String username) {
        service.deleteAdmin(username);
        return username + " Deleted from the List";
    }
}
