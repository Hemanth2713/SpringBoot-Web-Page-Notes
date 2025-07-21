package com.example.CarBookingTask.Service;

import com.example.CarBookingTask.Model.Maintenance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MaintenanceRepository extends JpaRepository<Maintenance,Long> {
}
