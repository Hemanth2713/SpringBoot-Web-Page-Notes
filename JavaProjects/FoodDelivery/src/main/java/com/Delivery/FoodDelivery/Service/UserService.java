package com.Delivery.FoodDelivery.Service;

import com.Delivery.FoodDelivery.Model.User;
import com.Delivery.FoodDelivery.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User addUSerDetails(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUserDetails() {
        return userRepository.findAll();
    }


    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found with ID : "+userId));
    }

    public List<User> getAllUsersByUsingRole(String role) {
        return userRepository.findByRole(role);
    }

    public boolean deleteUserById(Long userId) {
        if(userRepository.findById(userId).isPresent()){
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }

    public User updateUserDetails(Long userId, User userDetails) {
        User existingUser=userRepository.findById(userId).orElseThrow(()->new  RuntimeException("User not Found"));

    //User cant able to change his user_id, email and role
        existingUser.setUserId(existingUser.getUserId());
        existingUser.setEmail(existingUser.getEmail());
        existingUser.setRole(existingUser.getRole());

        existingUser.setUserName(userDetails.getUserName());
        existingUser.setAddress(userDetails.getAddress());
        existingUser.setPassword(userDetails.getPassword());
        existingUser.setPhoneNumber(userDetails.getPhoneNumber());

        return userRepository.save(existingUser);
    }

}