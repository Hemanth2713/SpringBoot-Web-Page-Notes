package com.example.CarBookingTask.Service;

import com.example.CarBookingTask.Model.Maintenance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaintenanceService {
    @Autowired
    private MaintenanceRepository repository;
    public Maintenance registerMaintenance(Maintenance maintenance) {
        return repository.save(maintenance);
    }

    public List<Maintenance> getAllMaintenanceDetails() {
        return repository.findAll();
    }
}
