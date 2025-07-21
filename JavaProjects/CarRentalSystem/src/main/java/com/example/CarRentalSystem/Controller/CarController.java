package com.example.CarRentalSystem.Controller;


import com.example.CarRentalSystem.Model.Car;
import com.example.CarRentalSystem.Service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("car")
@RestController
public class CarController {
    @Autowired
    CarService carService;
    @PostMapping()
    public ResponseEntity<Car> createCar(@RequestBody Car car){
        return new ResponseEntity<>(carService.createCar(car), HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<Car>> getAllCars(){
        List<Car> carList=carService.getAllCars();
        return new ResponseEntity<>(carList,HttpStatus.FOUND);
    }

    @GetMapping("Id/{carId}")
    public ResponseEntity<?> getCarById(@PathVariable Long carId){
        Car car=carService.getCarById(carId);
        if(car==null){
            return new ResponseEntity<>("Car with id "+carId+" is not Found",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(car,HttpStatus.FOUND);
    }

    @PutMapping("Id/{carId}")
    public ResponseEntity<?> updateCarByCarId(@PathVariable Long carId, @RequestBody Car carDetails){
        Car car=carService.updateCarByCarId(carId,carDetails);

        return new ResponseEntity<>(car,HttpStatus.CREATED);
    }
    @DeleteMapping("Id/{deleteCar}")
    public ResponseEntity<String> deleteCar(@PathVariable("deleteCar") Long carId ){
        try{
            if(carService.deleteById(carId)){
                return new ResponseEntity<>("Successfully Deleted",HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>("Car Id "+carId+" is Not Found",HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
