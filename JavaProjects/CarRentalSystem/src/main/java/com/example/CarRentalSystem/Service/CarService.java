package com.example.CarRentalSystem.Service;


import com.example.CarRentalSystem.Model.Car;
import com.example.CarRentalSystem.Repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {
    @Autowired
    CarRepository carRepository;

    public Car createCar(Car car) {
        return carRepository.save(car);
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Car getCarById(Long carId) {
        return carRepository.findById(carId).orElse(null);
    }

    public Car updateCarByCarId(Long carId, Car carDetails) {
        Car existingCar=carRepository.findById(carId).orElse(null);
        existingCar.setCarId(existingCar.getCarId());
        existingCar.setBrand(existingCar.getBrand());
        existingCar.setModel(existingCar.getModel());
        existingCar.setMileage(existingCar.getMileage());
        existingCar.setFuelTankCapacity(existingCar.getFuelTankCapacity());
        existingCar.setNumberPlate(existingCar.getNumberPlate());
        existingCar.setSeatingCapacity(existingCar.getSeatingCapacity());

        existingCar.setPricePerDay(carDetails.getPricePerDay());

        return carRepository.save(existingCar);

    }

    public boolean deleteById(Long carId) {
        if (carRepository.findById(carId).isPresent()){
            carRepository.deleteById(carId);
            return true;
        }
        return false;
    }
}
