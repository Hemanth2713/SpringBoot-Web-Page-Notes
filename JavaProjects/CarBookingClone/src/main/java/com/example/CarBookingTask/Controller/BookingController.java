//package com.example.CarBookingTask.Controller;
//
//
//import com.example.CarBookingTask.Model.Booking;
//import com.example.CarBookingTask.Service.BookingService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RequestMapping("/booking")
//@RestController
//public class BookingController {
//
//    @Autowired
//    private BookingService service;
//
//    @PostMapping("/bookingRegister")
//    public Booking registerBookingDetails(@RequestBody Booking booking)
//    {
//        return service.registerBooking(booking);
//    }
//
//    @GetMapping("/bookings")
//    public List<Booking> getAllBookingDetails(){
//        return service.getAllBookings();
//    }
//
//
//    @PostMapping("/book")
//    public String bookCar(
//            @RequestParam Long customerId,
//            @RequestParam Long carId,
//            @RequestParam String startDate,
//            @RequestParam String endDate) {
//        return service.bookCar(customerId, carId, startDate, endDate);
//    }
//
//
//}

package com.example.CarBookingTask.Controller;

import com.example.CarBookingTask.Model.Booking;
import com.example.CarBookingTask.Repository.BookingRequest;
import com.example.CarBookingTask.Service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    private BookingService service;

    @PostMapping("/bookingRegister")
    public Booking registerBookingDetails(@RequestBody Booking booking) {
        return service.registerBooking(booking);
    }

    @GetMapping("/bookings")
    public List<Booking> getAllBookingDetails() {
        return service.getAllBookings();
    }

    @PostMapping("/book")
    public String bookCar(@RequestBody BookingRequest bookingRequest) {
        return service.bookCar(
                bookingRequest.getCustomerId(),
                bookingRequest.getCarId(),
                bookingRequest.getStartDate(),
                bookingRequest.getEndDate()
        );
    }
}

