package com.practice.Task1.Controller;

import com.practice.Task1.Model.Booking;
import com.practice.Task1.Service.BookingService;  // Corrected import
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/bookCar")
    public String bookCar(@RequestBody Booking booking) {
        // Print the booking object to verify it's parsed correctly
        System.out.println(booking);

        // Call service to handle the booking logic
        return bookingService.bookCar(booking);
    }
}
