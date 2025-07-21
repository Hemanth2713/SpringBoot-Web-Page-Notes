package com.example.CarRentalSystem.Repository;


import com.example.CarRentalSystem.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByLicenseNumber(String licenseNumber);
}
