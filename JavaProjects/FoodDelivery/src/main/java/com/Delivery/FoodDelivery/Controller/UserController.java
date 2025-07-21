package com.Delivery.FoodDelivery.Controller;


import com.Delivery.FoodDelivery.Model.User;
import com.Delivery.FoodDelivery.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Request ID for PostgresSQL : REQ1248602

@RequestMapping("user")
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping()
    public ResponseEntity<User> addUSerDetails(@RequestBody User user){
        return new ResponseEntity<>(userService.addUSerDetails(user),HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<User>> getAllUserDetails(){
        List<User> userList=userService.getAllUserDetails();
        return new ResponseEntity<>(userList,HttpStatus.FOUND);
    }

    @GetMapping("Id/{userId}")
    public ResponseEntity<User> getUserByID(@PathVariable Long userId){
        User user=userService.getUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.FOUND);
    }

    @GetMapping("role/{role}")
    public ResponseEntity<List<User>> UsersByRole(@PathVariable String role){
        List<User> userList=userService.getAllUsersByUsingRole(role);
        return new ResponseEntity<>(userList,HttpStatus.FOUND);
    }
    @PutMapping("Id/{userId}")
    public ResponseEntity<User> updateUserDetails(@PathVariable Long userId,@RequestBody User userDetails){
        User user=userService.updateUserDetails(userId,userDetails);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @DeleteMapping("Id/{userId}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long userId){
        try{
            if(userService.deleteUserById(userId)){
                return new ResponseEntity<>("Successfully Deleted",HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(userId+" Not Found",HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}