package com.example.CarBookingTask.Repository;
import com.example.CarBookingTask.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Optional<Customer> findByLicenseNumber(String licenseNumber);
    Optional<Customer> findByEmail(String email);
}
