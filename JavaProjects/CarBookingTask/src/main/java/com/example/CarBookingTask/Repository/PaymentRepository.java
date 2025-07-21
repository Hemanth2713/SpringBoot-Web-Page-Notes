package com.example.CarBookingTask.Repository;

import com.example.CarBookingTask.Model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    // Additional custom queries can be defined here if needed
}
