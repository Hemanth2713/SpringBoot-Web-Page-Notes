package com.example.CarRentalSystem.Controller;


import com.example.CarRentalSystem.Model.User;
import com.example.CarRentalSystem.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("user")
@RestController
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> usersList=userService.getAllUsers();
        return new ResponseEntity<>(usersList,HttpStatus.FOUND);
    }

    @GetMapping("Id/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable Long userId){
        User user=userService.getUserById(userId);
        if(user==null){
            return new ResponseEntity<>("User with id "+userId+" is not Found",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user,HttpStatus.FOUND);
    }
    @GetMapping("licenseNumber/{licenseNumber}")
    public ResponseEntity<?> getUserByLicenseNumber(@PathVariable String licenseNumber){
        User user=userService.getUserByLicenseNumber(licenseNumber);
        if(user==null){
            return new ResponseEntity<>("User with LicenseNumber "+licenseNumber+" is not Found",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user,HttpStatus.FOUND);
    }

    @PutMapping("Id/{userId}")
    public ResponseEntity<?> updateUSerByUserId(@PathVariable Long userId,@RequestBody User userDetails){
        User user=userService.updateUSerByUserId(userId,userDetails);
        if(user==null){
            return new ResponseEntity<>("User with id "+userId+" is not Found",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user,HttpStatus.CREATED);
    }
    @DeleteMapping("Id/{deleteUser}")
    public ResponseEntity<String> deleteUser(@PathVariable("deleteUser") Long userId ){
        try{
            if(userService.deleteById(userId)){
                return new ResponseEntity<>("Successfully Deleted",HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>("User Id "+userId+" is Not Found",HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
