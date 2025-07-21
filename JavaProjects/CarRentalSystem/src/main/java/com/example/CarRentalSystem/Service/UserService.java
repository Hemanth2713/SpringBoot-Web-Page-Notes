package com.example.CarRentalSystem.Service;

import com.example.CarRentalSystem.Model.User;
import com.example.CarRentalSystem.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public User getUserByLicenseNumber(String licenseNumber) {
        return userRepository.findByLicenseNumber(licenseNumber).orElse(null);
    }

    public User updateUSerByUserId(Long userId, User userDetails) {
        User existingUser=userRepository.findById(userId).orElse(null);
        existingUser.setUserId(existingUser.getUserId());
        existingUser.setEmail(existingUser.getEmail());
        existingUser.setLicenseNumber(existingUser.getLicenseNumber());

        existingUser.setFirstName(userDetails.getFirstName());
        existingUser.setLastName(userDetails.getLastName());
        existingUser.setAddress(userDetails.getAddress());
        existingUser.setPassword(userDetails.getPassword());
        existingUser.setPhoneNumber(userDetails.getPhoneNumber());
        return userRepository.save(existingUser);
    }

    public boolean deleteById(Long userId) {
        if(userRepository.findById(userId).isPresent()){
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }
}
