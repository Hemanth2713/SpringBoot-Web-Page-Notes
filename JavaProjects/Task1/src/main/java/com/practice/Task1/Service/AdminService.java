package com.practice.Task1.Service;


import com.practice.Task1.Model.Admin;
import com.practice.Task1.Repo.AdminRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class AdminService {
    @Autowired
    private AdminRepo repo;

    public Admin saveAdmin(Admin admin) {
        return repo.save(admin);
    }

    public List<Admin> getAllAdmins() {
        return repo.findAll();
    }

    public Admin getAdminByUsername(String username) {
        return repo.findByUsername(username);
    }

    public void deleteAdmin(String username) {
        repo.deleteByUsername(username);
    }
}
