package com.example.CarBookingTask.Service;

import com.example.CarBookingTask.Model.Car;
import com.example.CarBookingTask.Repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    @Autowired
    private CarRepository repository;

    public Car registerCar(Car car) {
        return repository.save(car);
    }

    public List<Car> getAllCars() {
        return repository.findAll();
    }

    public Car getCarById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<Car> getByCarCompany(String company) {
        return repository.findByCompany(company);
    }
    // Get cars by model
    public List<Car> getByCarModel(String model) {
        return repository.findByModel(model);
    }

    // Get cars by seating capacity
    public List<Car> getByCarSeatingCapacity(Integer seatingCapacity) {
        return repository.findBySeatingCapacity(seatingCapacity);
    }

    // Get cars by company and model
    public List<Car> getByCarCompanyAndModel(String company, String model) {
        return repository.findByCompanyAndModel(company, model);
    }
}
