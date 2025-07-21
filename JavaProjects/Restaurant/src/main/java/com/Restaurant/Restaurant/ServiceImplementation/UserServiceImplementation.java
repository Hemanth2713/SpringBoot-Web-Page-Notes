package com.Restaurant.Restaurant.ServiceImplementation;

import com.Restaurant.Restaurant.DTO.ChangePasswordRequest;
import com.Restaurant.Restaurant.DTO.UserResponseDto;
import com.Restaurant.Restaurant.DTO.UserUpdateDto;
import com.Restaurant.Restaurant.Model.User;
import com.Restaurant.Restaurant.Repository.UserRepository;
import com.Restaurant.Restaurant.Service.UserService;
import com.Restaurant.Restaurant.utils.CustomUtils;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImplementation implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public User createUser(User user) {
        log.info("Creating new user with email: {}", user.getEmail());
        User savedUser = userRepository.save(user);
        log.info("User created successfully with ID: {}", savedUser.getUserId());
        return savedUser;
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        log.info("Fetching all users from the database");
        List<UserResponseDto> userResponseDtoList = userRepository.findAll()
                .stream()
                .map(UserResponseDto::new)
                .collect(Collectors.toList());
        log.debug("Total users fetched: {}", userResponseDtoList.size());
        return userResponseDtoList;
    }

    @Override
    public Optional<UserResponseDto> getUserById(Long userId) {
        log.info("Fetching user with ID: {}", userId);
        Optional<UserResponseDto> userResponseDto = userRepository.findById(userId)
                .map(user -> modelMapper.map(user, UserResponseDto.class));

        if (userResponseDto.isPresent()) {
            log.info("User found with ID: {}", userId);
        } else {
            log.warn("User not found with ID: {}", userId);
        }

        return userResponseDto;
    }

    @Override
    public UserResponseDto updateCurrentUser(Long userId, UserUpdateDto userUpdateDto) {
        log.info("Updating user with ID: {}", userId);

        Optional<User> existingUser = userRepository.findById(userId);

        if (existingUser.isPresent()) {
            User updateUser = existingUser.get();

            if (userUpdateDto.getName() != null) {
                log.debug("Updating name to: {}", userUpdateDto.getName());
                updateUser.setName(userUpdateDto.getName());
            }

            if (userUpdateDto.getMobileNo() != null) {
                log.debug("Updating mobile number to: {}", userUpdateDto.getMobileNo());
                updateUser.setMobileNo(userUpdateDto.getMobileNo());
            }

            User savedUser = userRepository.save(updateUser);
            log.info("User with ID {} updated successfully", userId);

            return modelMapper.map(savedUser, UserResponseDto.class);
        } else {
            log.error("User with ID {} not found for update", userId);
            throw new UsernameNotFoundException("User with ID " + userId + " not available");
        }
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        log.info("Deleting user with ID: {}", id);
        userRepository.deleteById(id);
        log.info("User deleted successfully with ID: {}", id);
    }

    @Override
    public String changePassword(ChangePasswordRequest changePasswordRequest) {
        User currentLoggedInUser= CustomUtils.getCurrentLoggedInUser();
        if(!passwordEncoder.matches(changePasswordRequest.getCurrentPassword(),currentLoggedInUser.getPassword())){
            log.warn("Password change failed: current password is incorrect");
            throw new BadCredentialsException("Current password is incorrect");
        }

        if(changePasswordRequest.getNewPassword().equals(changePasswordRequest.getConfirmPassword())){
            log.warn("Password change failed: new password and confirm password do not match");
            throw new IllegalArgumentException("New password and confirm password do not match");
        }
        currentLoggedInUser.setPassword(passwordEncoder.encode(changePasswordRequest.getNewPassword()));
        userRepository.save(currentLoggedInUser);
        log.info("Password changed successfully for user ID: {}", currentLoggedInUser.getUserId());
        return "Password changed successfully.";
    }
}
