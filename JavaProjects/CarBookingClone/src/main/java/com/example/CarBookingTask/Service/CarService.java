package com.example.CarBookingTask.Service;

import com.example.CarBookingTask.Model.Car;
import com.example.CarBookingTask.Repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    /**
     * Registers a new car in the system.
     *
     * @param car the car to be registered
     * @return the saved car entity
     */
    public Car registerCar(Car car) {
        return carRepository.save(car);
    }

    /**
     * Retrieves a list of all cars.
     *
     * @return list of all cars
     */
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    /**
     * Retrieves a car by its ID.
     *
     * @param id the ID of the car
     * @return the car with the given ID
     * @throws ResourceNotFoundException if the car is not found
     */
    public Car getCarById(Long id) {
        return carRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Car not found with ID: " + id));
    }

    /**
     * Retrieves a list of cars by the company name.
     *
     * @param company the car company name
     * @return list of cars by the company
     */
    public List<Car> getByCarCompany(String company) {
        List<Car> cars = carRepository.findByCompany(company);
        if (cars.isEmpty()) {
            throw new ResourceNotFoundException("No cars found for the company: " + company);
        }
        return cars;
    }

    /**
     * Retrieves a list of cars by the model name.
     *
     * @param model the car model name
     * @return list of cars by the model
     */
    public List<Car> getByCarModel(String model) {
        List<Car> cars = carRepository.findByModel(model);
        if (cars.isEmpty()) {
            throw new ResourceNotFoundException("No cars found for the model: " + model);
        }
        return cars;
    }

    /**
     * Retrieves a list of cars by seating capacity.
     *
     * @param seatingCapacity the seating capacity of the car
     * @return list of cars with the specified seating capacity
     */
    public List<Car> getByCarSeatingCapacity(Integer seatingCapacity) {
        List<Car> cars = carRepository.findBySeatingCapacity(seatingCapacity);
        if (cars.isEmpty()) {
            throw new ResourceNotFoundException("No cars found with seating capacity: " + seatingCapacity);
        }
        return cars;
    }

    /**
     * Retrieves a list of cars by company and model.
     *
     * @param company the car company name
     * @param model   the car model name
     * @return list of cars matching the company and model
     */
    public List<Car> getByCarCompanyAndModel(String company, String model) {
        List<Car> cars = carRepository.findByCompanyAndModel(company, model);
        if (cars.isEmpty()) {
            throw new ResourceNotFoundException("No cars found for company: " + company + " and model: " + model);
        }
        return cars;
    }

    /**
     * Updates an existing car's details.
     *
     * @param id  the ID of the car to update
     * @param car the updated car details
     * @return the updated car entity
     * @throws ResourceNotFoundException if the car is not found
     */
    public Car updateCar(Long id, Car car) {
        Car existingCar = carRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Car not found with ID: " + id));

        existingCar.setCompany(car.getCompany());
        existingCar.setModel(car.getModel());
        existingCar.setSeatingCapacity(car.getSeatingCapacity());
        existingCar.setPrice(car.getPrice()); // Assuming 'price' is a field

        return carRepository.save(existingCar);
    }

    /**
     * Deletes a car by its ID.
     *
     * @param id the ID of the car to delete
     * @throws ResourceNotFoundException if the car is not found
     */
    public void deleteCar(Long id) {
        Car existingCar = carRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Car not found with ID: " + id));
        carRepository.delete(existingCar);
    }
}
