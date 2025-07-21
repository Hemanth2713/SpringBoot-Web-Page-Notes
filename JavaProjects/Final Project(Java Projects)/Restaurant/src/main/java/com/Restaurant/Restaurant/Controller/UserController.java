package com.Restaurant.Restaurant.Controller;

import com.Restaurant.Restaurant.DTO.ChangePasswordRequest;
import com.Restaurant.Restaurant.DTO.UserResponseDto;
import com.Restaurant.Restaurant.DTO.UserUpdateDto;
import com.Restaurant.Restaurant.Model.User;
import com.Restaurant.Restaurant.Service.UserService;
import com.Restaurant.Restaurant.utils.CustomUtils;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/api/users")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/allUser")
    private ResponseEntity<List<UserResponseDto>> getAllUserDetails() {
        log.info("Fetching all user details");

        List<UserResponseDto> userResponseDtoList = userService.getAllUsers();
        log.debug("Fetched user list: {}", userResponseDtoList);

        return new ResponseEntity<>(userResponseDtoList, HttpStatus.FOUND);
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponseDto> getCurrentUser() {
        log.info("Fetching the currently logged-in user");

        User currentUser = CustomUtils.getCurrentLoggedInUser();
        log.debug("Current user entity: {}", currentUser);

        UserResponseDto userResponseDto = this.modelMapper.map(currentUser, UserResponseDto.class);
        log.debug("Mapped UserResponseDto: {}", userResponseDto);

        log.info("Returning user details");
        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    @PutMapping("/updateUser")
    private ResponseEntity<UserResponseDto> updateCurrentUser(@Valid @RequestBody UserUpdateDto userUpdateDto) {
        log.info("Updating current user with data: {}", userUpdateDto);

        User currentUser = CustomUtils.getCurrentLoggedInUser();
        log.debug("Current user ID: {}", currentUser.getUserId());

        UserResponseDto userResponseDto = userService.updateCurrentUser(currentUser.getUserId(), userUpdateDto);
        log.debug("Updated user response: {}", userResponseDto);

        log.info("User update successful");
        return new ResponseEntity<>(userResponseDto, HttpStatus.CREATED);
    }
    @PostMapping("/changePassword")
    private ResponseEntity<String> changePassword(@Valid @RequestBody ChangePasswordRequest changePasswordRequest) {
        log.info("Password change request received");

        User currentUser = CustomUtils.getCurrentLoggedInUser();
        log.debug("Current user initiating password change: {}", currentUser.getEmail());

        String passwordUpdate = userService.changePassword(changePasswordRequest);
        log.info("Password updated successfully for user: {}", currentUser.getEmail());

        return new ResponseEntity<>(passwordUpdate, HttpStatus.CREATED);
    }

}
