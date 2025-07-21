package com.Restaurant.Restaurant.ServiceImplementation;

import com.Restaurant.Restaurant.DTO.LoginDto;
import com.Restaurant.Restaurant.DTO.UserRequestDto;
import com.Restaurant.Restaurant.Model.ActiveStatus;
import com.Restaurant.Restaurant.Model.Role;
import com.Restaurant.Restaurant.Model.User;
import com.Restaurant.Restaurant.Repository.UserRepository;
import com.Restaurant.Restaurant.Service.AuthorizationService;
import com.Restaurant.Restaurant.utils.JWTService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class AuthorizationServiceImplementation implements AuthorizationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Autowired
    private AuthenticationManager authmanager;


    @Autowired
    private JWTService jwtService;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public String registerUser(UserRequestDto userRequestDto, Role role) {
        log.info("Attempting to register user with email: {}", userRequestDto.getEmail());

        User user = userRepository.findByEmail(userRequestDto.getEmail());

        if (user == null) {
            if (!userRequestDto.getPassword().equals(userRequestDto.getConfirmPassword())) {
                log.warn("Password mismatch for email: {}", userRequestDto.getEmail());
                throw new IllegalArgumentException("Passwords do not match");
            }

            User saveUser = this.modelMapper.map(userRequestDto, User.class);
            saveUser.setPassword(this.encoder.encode(saveUser.getPassword()));
            saveUser.setRole(role);
            saveUser.setStatus(ActiveStatus.ACTIVE);

            userRepository.save(saveUser);
            log.info("User registered successfully with email: {}", userRequestDto.getEmail());

            return "SUCCESS";
        }

        log.warn("Registration failed: User already exists with email: {}", userRequestDto.getEmail());
        throw new DuplicateKeyException("User already exists");
    }

    @Override
    public Map<String, String> loginUser(LoginDto loginDto) {
        log.info("Attempting login for username: {}", loginDto.getEmail());
        Authentication authentication=authmanager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(),loginDto.getPassword()));
        if(authentication.isAuthenticated()){
            User user=userRepository.findByEmail(loginDto.getEmail());
            String token=jwtService.generateToken(loginDto.getEmail(),user.getRole());
            Map<String,String> response=new HashMap<>();
            response.put("token",token);
            log.info("Login successful for username: {}", loginDto.getEmail());
            return response;
        }
        else {
            log.warn("Login failed for username: {}", loginDto.getEmail());
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "failure");
            return errorResponse;
        }
    }
}
