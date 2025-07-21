//package com.example.CarBookingTask.Service;
//
//import com.example.CarBookingTask.Model.Booking;
//import com.example.CarBookingTask.Model.Car;
//import com.example.CarBookingTask.Model.Customer;
//import com.example.CarBookingTask.Repository.BookingRepository;
//import com.example.CarBookingTask.Repository.CarRepository;
//import com.example.CarBookingTask.Repository.CustomerRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDate;
//import java.util.List;
//
//@Service
//public class BookingService {
//    @Autowired
//    private BookingRepository repository;
//
//    @Autowired
//    private CarRepository carRepository;
//
//    @Autowired
//    private CustomerRepository customerRepository;
//
//
//
//
//    public Booking registerBooking(Booking booking) {
//        return repository.save(booking);
//    }
//
//    public List<Booking> getAllBookings() {
//        return repository.findAll();
//    }
//
//
//    public String bookCar(Long customerId, Long carId, String startDate, String endDate) {
//        // Fetch Customer
//        Customer customer = customerRepository.findById(customerId)
//                .orElseThrow(() -> new RuntimeException("Customer not found"));
//
//        // Fetch Car
//        Car car = carRepository.findById(carId)
//                .orElseThrow(() -> new RuntimeException("Car not found"));
//
//        // Check if the car is available
//        if (!"AVAILABLE".equals(car.getStatus())) {
//            return "Car is not available for booking.";
//        }
//
//        // Create Booking
//        Booking booking = new Booking();
//        booking.setCar(car);
//        booking.setCustomer(customer);
//        booking.setBookingDate(LocalDate.now().toString());
//        booking.setStartDate(startDate);
//        booking.setEndDate(endDate);
//
//        // Calculate total price (Assuming pricePerDay * number of days)
//        long days = LocalDate.parse(startDate).until(LocalDate.parse(endDate)).getDays();
//        booking.setTotalPrice(car.getPricePerDay() * days);
//
//        booking.setPaymentStatus("PENDING");
//        booking.setApprovalStatus("PENDING");
//
//        // Save Booking
//        repository.save(booking);
//
//        // Update Car Status
//        car.setStatus("BOOKED");
//        carRepository.save(car);
//
//        return "Booking successful! Booking ID: " + booking.getBookingId();
//    }
//}


package com.example.CarBookingTask.Service;

import com.example.CarBookingTask.Model.Booking;
import com.example.CarBookingTask.Model.Car;
import com.example.CarBookingTask.Model.Customer;
import com.example.CarBookingTask.Repository.BookingRepository;
import com.example.CarBookingTask.Repository.CarRepository;
import com.example.CarBookingTask.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public Booking registerBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public String bookCar(Long customerId, Long carId, String startDate, String endDate) {
        // Validate customer
        Optional<Customer> customerOpt = customerRepository.findById(customerId);
        if (customerOpt.isEmpty()) {
            return "Customer not found!";
        }

        // Validate car availability
        Car car = carRepository.findByCarIdAndStatus(carId, "AVAILABLE");
        if (car == null) {
            return "Car not available or does not exist!";
        }

        // Calculate total price
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        long days = ChronoUnit.DAYS.between(start, end);
        if (days <= 0) {
            return "Invalid date range!";
        }
        double totalPrice = days * car.getPricePerDay();

        // Create booking
        Booking booking = new Booking();
        booking.setCustomer(customerOpt.get());
        booking.setCar(car);
        booking.setStartDate(startDate);
        booking.setEndDate(endDate);
        booking.setTotalPrice(totalPrice);
        booking.setPaymentStatus("PENDING");
        booking.setApprovalStatus("PENDING");

        // Save booking
        bookingRepository.save(booking);

        // Update car status to BOOKED
        car.setStatus("BOOKED");
        carRepository.save(car);

        return "Booking successful! Total Price: " + totalPrice;
    }
}

