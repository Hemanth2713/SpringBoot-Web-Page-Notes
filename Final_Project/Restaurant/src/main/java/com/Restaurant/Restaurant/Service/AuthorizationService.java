package com.Restaurant.Restaurant.Service;

import com.Restaurant.Restaurant.DTO.LoginDto;
import com.Restaurant.Restaurant.DTO.UserRequestDto;
import com.Restaurant.Restaurant.Model.Role;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface AuthorizationService {

    String registerUser(@Valid UserRequestDto userRequestDto, Role role);

    Map<String, String> loginUser(@Valid LoginDto loginDto);
}
