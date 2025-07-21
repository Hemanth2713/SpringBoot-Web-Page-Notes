package com.example.CarBookingTask.Repository;

import com.example.CarBookingTask.Model.AdminDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<AdminDetails,Integer> {
}
