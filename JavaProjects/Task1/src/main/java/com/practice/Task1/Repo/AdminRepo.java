package com.practice.Task1.Repo;

import com.practice.Task1.Model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepo extends JpaRepository<Admin,Integer> {
    Admin findByUsername(String username);

    void deleteByUsername(String username);
}
