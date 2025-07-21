package com.practice.Task1.Service;

import com.practice.Task1.Model.Car;
import com.practice.Task1.Repo.carRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class carService {
    @Autowired
    private carRepo repo;

    public List<Car> getAllCars() {
        return  repo.findAll();
    }

    public Car getByCarId(int id) {
        return repo.findById(id).orElse(new Car());
    }

    public void addNewCar(Car car) {
        repo.save(car);
    }

    public void updateCar(Car car) {
        repo.save(car);
    }

    public void deleteCar(int id) {
        repo.deleteById(id);
    }

    public List<Car> searchByCar(String name) {
        return  repo.findByCarName(name);
    }
}
