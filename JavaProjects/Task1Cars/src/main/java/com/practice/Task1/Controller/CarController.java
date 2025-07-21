package com.practice.Task1.Controller;

import com.practice.Task1.Model.Car;
import com.practice.Task1.Service.carService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CarController {

    @Autowired
    private carService service;
    @GetMapping("hello")
    public  String running(){
        return  "CAr is Running" ;
    }

    @GetMapping("running")
    public String created(){
        return  "Car table created";
    }

    @GetMapping("cars")
    public List<Car> allCars(){
        return service.getAllCars();
    }

    @GetMapping("car/{carId}")
    public Car getCarByUsingId(@PathVariable int carId){
        return service.getByCarId(carId);
    }

    @PostMapping("addCar")
    public Car addingCar(@RequestBody Car car){
        service.addNewCar(car);
        return service.getByCarId(car.getCarId());
    }

    @PutMapping("updateCar")
    public  Car updatingCar(@RequestBody  Car car){
        service.updateCar(car);
        return service.getByCarId(car.getCarId());
    }


    @DeleteMapping("deleteCar/{id}")
    public  String deletingCar(@PathVariable int id){
        service.deleteCar(id);
        return "Car Removed from the List";
    }

    @GetMapping("carname/{name}")
    public List<Car> searchByCarName(@PathVariable("name") String name) {
        return service.searchByCar(name);
    }

}
