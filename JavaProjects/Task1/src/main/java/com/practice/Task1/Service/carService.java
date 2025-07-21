package com.practice.Task1.Service;

import com.practice.Task1.Model.Booking;
import com.practice.Task1.Model.Car;
import com.practice.Task1.Model.Customer;
import com.practice.Task1.Repo.BookingRepository;
import com.practice.Task1.Repo.CustomerRepo;
import com.practice.Task1.Repo.carRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Service
    public static class BookingService {

        @Autowired
        private carRepo carRepository;

        @Autowired
        private CustomerRepo customerRepository;

        @Autowired
        private BookingRepository bookingRepository;

        public String bookCar(int customerId, int carId) {
            // Check if car exists
            Car car = carRepository.findById(carId).orElse(null);
            if (car == null) {
                return "Car not found!";
            }

            // Check if customer exists
            Customer customer = customerRepository.findById(customerId).orElse(null);
            if (customer == null) {
                return "Customer not found!";
            }

            // Check if car is already booked
            if (car.getBookings() != null && car.getBookings().stream().anyMatch(booking -> booking.isBooked())) {
                return "Car is already booked!";
            }

            // Create booking
            Booking booking = new Booking();
            booking.setCar(car);
            booking.setCustomer(customer);
            booking.setBooked(true); // Set the car as booked
            bookingRepository.save(booking);

            return "Booking successful!";
        }
    }
}
