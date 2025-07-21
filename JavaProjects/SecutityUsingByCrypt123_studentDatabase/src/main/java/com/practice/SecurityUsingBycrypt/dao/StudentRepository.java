package com.practice.SecurityUsingBycrypt.dao;

import com.practice.SecurityUsingBycrypt.model.studentSecurity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<studentSecurity, Long> {
}
