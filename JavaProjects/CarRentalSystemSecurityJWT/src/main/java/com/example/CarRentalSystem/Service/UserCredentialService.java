package com.example.CarRentalSystem.Service;

import com.example.CarRentalSystem.Model.UserCredentials;
import com.example.CarRentalSystem.Repository.UserCredentialsRepository;
import com.example.CarRentalSystem.SecurityData.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserCredentialService implements UserDetailsService {

    @Autowired
    private UserCredentialsRepository userCredentialsRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserCredentials credentials = userCredentialsRepository.findByEmail(email);
        if (credentials == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
        return new CustomUserDetails(credentials);
    }
}