package com.example.CarBookingTask.Service;

import com.example.CarBookingTask.Model.AdminDetails;
import com.example.CarBookingTask.Repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private AdminRepository repo;
    public AdminDetails registerAdmin(AdminDetails adminDetails) {
        return repo.save(adminDetails);
    }

    public List<AdminDetails> getAlAdmins() {
        return repo.findAll();
    }
}
