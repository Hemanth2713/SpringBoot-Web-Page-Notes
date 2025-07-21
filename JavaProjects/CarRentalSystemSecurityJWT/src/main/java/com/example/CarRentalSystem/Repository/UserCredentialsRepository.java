package com.example.CarRentalSystem.Repository;

import com.example.CarRentalSystem.Model.UserCredentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCredentialsRepository extends JpaRepository<UserCredentials, String> {
    UserCredentials findByEmail(String email);
}