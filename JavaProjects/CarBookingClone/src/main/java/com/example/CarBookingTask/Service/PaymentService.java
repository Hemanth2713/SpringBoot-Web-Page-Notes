package com.example.CarBookingTask.Service;


import com.example.CarBookingTask.Model.Booking;
import com.example.CarBookingTask.Model.Customer;
import com.example.CarBookingTask.Model.Payment;
import com.example.CarBookingTask.Repository.BookingRepository;
import com.example.CarBookingTask.Repository.CustomerRepository;
import com.example.CarBookingTask.Repository.PaymentRepository;
import com.example.CarBookingTask.Repository.PaymentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public String makePayment(PaymentRequest paymentRequest) {
        // Validate booking exists
        Optional<Booking> bookingOptional = bookingRepository.findById(paymentRequest.getBookingId());
        if (bookingOptional.isEmpty()) {
            return "Booking not found for ID: " + paymentRequest.getBookingId();
        }
        Booking booking = bookingOptional.get();

        // Validate customer exists
        Optional<Customer> customerOptional = customerRepository.findById(paymentRequest.getCustomerId());
        if (customerOptional.isEmpty()) {
            return "Customer not found for ID: " + paymentRequest.getCustomerId();
        }

        // Create Payment entity
        Payment payment = new Payment();
        payment.setBooking(booking);
        payment.setCustomer(customerOptional.get());
        payment.setPaymentAmount(paymentRequest.getPaymentAmount());
        payment.setPaymentDate(LocalDateTime.now());
        payment.setPaymentStatus("COMPLETED"); // Payment is completed
        payment.setPaymentMethod(paymentRequest.getPaymentMethod());
        payment.setCurrency("USD"); // Default currency

        // Save payment record
        paymentRepository.save(payment);

        // Update Booking status and Approval status
        booking.setPaymentStatus("PAID"); // Mark booking as paid
        booking.setApprovalStatus("APPROVED"); // Mark approval as approved
        bookingRepository.save(booking);

        return "Payment successfully processed and Booking status updated for Booking ID: " + paymentRequest.getBookingId();
    }
}
