package com.example.CarBookingTask.Controller;

import com.example.CarBookingTask.Model.Car;
import com.example.CarBookingTask.Service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/car")
@RestController
public class CarController {
    @Autowired
    private CarService service;

    @PostMapping("/registerCar")
    public Car registerCarDetails(@RequestBody Car car){
        return service.registerCar(car);
    }

    @GetMapping("/cars")
    public List<Car> getAllCarDetails(){
        return service.getAllCars();
    }

    @GetMapping("/carById/{id}")
    public ResponseEntity<?> getCarByUsingId(@PathVariable Long id){
        Car car= service.getCarById(id);
        if (car == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Car with car number " + id + " does not exist");
        }
        return ResponseEntity.ok(car);
    }


    @GetMapping("/carByCompany/{company}")
    public ResponseEntity<?> getCarByCompanyName(@PathVariable String company) {
        List<Car> cars = service.getByCarCompany(company);
        if (cars.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No cars found for company: " + company);
        }
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/carByModel/{model}")
    public ResponseEntity<?> getCarByModel(@PathVariable String model) {
        List<Car> cars = service.getByCarModel(model);
        if (cars.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No cars found for model: " + model);
        }
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/carBySeatingCapacity/{seatingCapacity}")
    public ResponseEntity<?> getCarBySeatingCapacity(@PathVariable Integer seatingCapacity) {
        List<Car> cars = service.getByCarSeatingCapacity(seatingCapacity);
        if (cars.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No cars found with seating capacity: " + seatingCapacity);
        }
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/carByCompanyAndModel")
    public ResponseEntity<?> getCarByCompanyAndModel(
            @RequestParam String company,
            @RequestParam String model) {
        List<Car> cars = service.getByCarCompanyAndModel(company, model);
        if (cars.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No cars found for company: " + company + " and model: " + model);
        }
        return ResponseEntity.ok(cars);
    }

}
