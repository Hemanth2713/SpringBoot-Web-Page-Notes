package com.example.CarBookingTask.Controller;

import com.example.CarBookingTask.Model.Maintenance;
import com.example.CarBookingTask.Service.MaintenanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/maintenance")
@RestController
public class MaintenanceController {
    @Autowired

    private MaintenanceService service;

    @PostMapping("maintenanceRegister")
    public Maintenance maintenanceRegisterDetails(@RequestBody Maintenance maintenance){
        return service.registerMaintenance(maintenance);
    }

    @GetMapping("/maintenanceData")
    public List<Maintenance> maintenanceDetails(){
        return service.getAllMaintenanceDetails();
    }
}
