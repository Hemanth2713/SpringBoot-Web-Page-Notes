package com.example.StudentH2DataBase.Repository;

import com.example.StudentH2DataBase.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}