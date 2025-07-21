package com.Restaurant.Restaurant.Service;

import com.Restaurant.Restaurant.DTO.ChangePasswordRequest;
import com.Restaurant.Restaurant.DTO.UserResponseDto;
import com.Restaurant.Restaurant.DTO.UserUpdateDto;
import com.Restaurant.Restaurant.Model.User;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User createUser(User user);
    List<UserResponseDto> getAllUsers();

    Optional<UserResponseDto> getUserById(@Valid Long userId);

    UserResponseDto updateCurrentUser(Long userId, @Valid UserUpdateDto userUpdateDto);

    void deleteUser(Long id);

    String changePassword(@Valid ChangePasswordRequest changePasswordRequest);
}
