package com.practice.Task1.Service;

import com.practice.Task1.Model.Booking;
import com.practice.Task1.Model.Car;
import com.practice.Task1.Model.Customer;
import com.practice.Task1.Repo.BookingRepository;
import com.practice.Task1.Repo.CustomerRepo;
import com.practice.Task1.Repo.carRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private carRepo carRepository;

    @Autowired
    private CustomerRepo customerRepository;

    // Method to handle booking logic
    public String bookCar(Booking booking) {
        // Fetch Car and Customer using IDs from the Booking object
        Car car = carRepository.findById(booking.getCar().getCarId()).orElse(null);
        Customer customer = customerRepository.findById(booking.getCustomer().getUserId()).orElse(null);

        // If Car or Customer is not found, return an error message
        if (car == null || customer == null) {
            return "Car or Customer not found!";
        }

        // Set the fetched Car and Customer in the Booking entity
        booking.setCar(car);
        booking.setCustomer(customer);
        booking.setBooked(true); // Mark the booking as successful

        // Save the Booking entity to the database
        bookingRepository.save(booking);
        return "Booking successful!";
    }
}
