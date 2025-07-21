package com.example.CarRentalSystem.Repository;


import com.example.CarRentalSystem.Model.User;
import com.example.CarRentalSystem.Model.UserCredentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByLicenseNumber(String licenseNumber);
    //UserCredentials findByEmail(String email);
}
