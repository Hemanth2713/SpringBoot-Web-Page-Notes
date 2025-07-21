package com.example.CarRentalSystem.Controller;

import com.example.CarRentalSystem.Model.Booking;
import com.example.CarRentalSystem.Service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping
    public ResponseEntity<?> createBooking(@RequestBody Booking booking) {
        try {
            Booking createdBooking = bookingService.createBooking(booking);
            return new ResponseEntity<>(createdBooking, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create booking: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Booking>> getAllBookings() {
        List<Booking> bookingsList = bookingService.getAllBookings();
        return new ResponseEntity<>(bookingsList, HttpStatus.OK);
    }

    @GetMapping("/id/{bookingId}")
    public ResponseEntity<?> getBookingById(@PathVariable Long bookingId) {
        Booking booking = bookingService.getBookingById(bookingId);
        if (booking == null) {
            return new ResponseEntity<>("Booking with id " + bookingId + " is not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(booking, HttpStatus.OK);
    }

    @PutMapping("/id/{bookingId}")
    public ResponseEntity<?> updateBookingById(@PathVariable Long bookingId, @RequestBody Booking bookingDetails) {
        try {
            Booking booking = bookingService.updateBookingById(bookingId, bookingDetails);
            if (booking == null) {
                return new ResponseEntity<>("Booking with id " + bookingId + " is not found", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(booking, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/id/{bookingId}")
    public ResponseEntity<?> deleteBooking(@PathVariable("bookingId") Long bookingId) {
        try {
            if (bookingService.deleteById(bookingId)) {
                return new ResponseEntity<>("Successfully deleted booking with id " + bookingId, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Booking with id " + bookingId + " is not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete booking: " + e.getMessage());
        }
    }
}