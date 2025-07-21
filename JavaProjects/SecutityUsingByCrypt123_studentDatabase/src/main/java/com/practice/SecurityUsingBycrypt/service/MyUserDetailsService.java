package com.practice.SecurityUsingBycrypt.service;

import com.practice.SecurityUsingBycrypt.dao.StudentRepository;
import com.practice.SecurityUsingBycrypt.dao.UserRepo;
import com.practice.SecurityUsingBycrypt.model.User;
import com.practice.SecurityUsingBycrypt.model.UserPrincipal;
import com.practice.SecurityUsingBycrypt.model.studentSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo repo;
    private StudentRepository studentRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repo.findByUsername(username);
        if (user == null) {
            System.out.println("User 404");
            throw new UsernameNotFoundException("User not found");
        }
        return new UserPrincipal(user);
    }



    public studentSecurity getStudent(Long id) {
        // Ensure that the ID is being properly matched with the repository
        return studentRepository.findById(id).orElse(new studentSecurity());  // Return an empty Student object if not found
    }

    public List<studentSecurity> geAllStudents() {
        return studentRepository.findAll();
    }

    public void addStudent(studentSecurity student) {
        studentRepository.save(student);
    }
}


