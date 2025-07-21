package com.practice.Task1.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookingId;

    @ManyToOne
    private Car car;  // Many bookings can refer to one car

    @ManyToOne
    private Customer customer;  // Many bookings can refer to one customer

    private String bookingDate; // You can store the booking date as a string or use LocalDate
    private boolean isBooked;   // A flag to mark whether a car is booked
}
