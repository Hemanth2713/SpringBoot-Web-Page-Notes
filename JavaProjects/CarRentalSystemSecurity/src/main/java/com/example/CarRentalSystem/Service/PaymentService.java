//package com.example.CarRentalSystem.Service;
//
//import com.example.CarRentalSystem.Model.Booking;
//import com.example.CarRentalSystem.Model.Payment;
//import com.example.CarRentalSystem.Repository.BookingRepository;
//import com.example.CarRentalSystem.Repository.PaymentRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.sql.Timestamp;
//
//@Service
//public class PaymentService {
//
//    @Autowired
//    private PaymentRepository paymentRepository;
//
//    @Autowired
//    private BookingRepository bookingRepository;
//
//    @Transactional
//    public Payment updatePaymentStatus(Long paymentId, Payment.PaymentStatus status, Payment.PaymentMethod method, Double amountPaid) {
//        Payment payment = paymentRepository.findById(paymentId)
//                .orElseThrow(() -> new RuntimeException("Payment not found"));
//
//        if (payment.getPaymentMethod() == null) {
//            throw new IllegalArgumentException("Payment method must be selected.");
//        }
//
//        payment.setPaymentStatus(status);
//        payment.setPaymentMethod(method);
//        payment.setPaymentDate(new Timestamp(System.currentTimeMillis())); // Store the payment time
//        paymentRepository.save(payment);
//
//        // If payment is completed, update booking status
//        if (status == Payment.PaymentStatus.Completed) {
//            Booking booking = payment.getBooking();
//            booking.setStatus(Booking.BookingStatus.Completed);
//            bookingRepository.save(booking);
//        }
//
//        return payment;
//    }
//}
////package com.example.CarRentalSystem.Service;
////
////import com.example.CarRentalSystem.Model.Booking;
////import com.example.CarRentalSystem.Model.Payment;
////import com.example.CarRentalSystem.Repository.BookingRepository;
////import com.example.CarRentalSystem.Repository.PaymentRepository;
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.stereotype.Service;
////import org.springframework.transaction.annotation.Transactional;
////
////@Service
////public class PaymentService {
////
////    @Autowired
////    private PaymentRepository paymentRepository;
////
////    @Autowired
////    private BookingRepository bookingRepository;
////
////    @Transactional
////    public Payment updatePaymentStatus(Long paymentId, Payment.PaymentStatus status, Payment.PaymentMethod method) {
////        Payment payment = paymentRepository.findById(paymentId)
////                .orElseThrow(() -> new RuntimeException("Payment not found"));
////
////        if (payment.getPaymentMethod() == null) {
////            throw new IllegalArgumentException("Payment method must be selected.");
////        }
////
////        payment.setPaymentStatus(status);
////        payment.setPaymentMethod(method);
////        paymentRepository.save(payment);
////
////        // If payment is completed, update booking status
////        if (status == Payment.PaymentStatus.Completed) {
////            Booking booking = payment.getBooking();
////            booking.setStatus(Booking.BookingStatus.Completed);
////            bookingRepository.save(booking);
////        }
////
////        return payment;
////    }
////}
package com.example.CarRentalSystem.Service;

import com.example.CarRentalSystem.Model.Booking;
import com.example.CarRentalSystem.Model.Payment;
import com.example.CarRentalSystem.Repository.BookingRepository;
import com.example.CarRentalSystem.Repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Transactional
    public Payment updatePaymentStatus(Long paymentId, Payment.PaymentStatus status, Payment.PaymentMethod method, Double amountPaid) {
        // Find the payment
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        // Check if payment is already completed
        if (payment.getPaymentStatus() == Payment.PaymentStatus.Completed) {
            throw new IllegalStateException("Cannot modify a completed payment.");
        }

        // Validate payment method
        if (method == null || method == Payment.PaymentMethod.None || method == Payment.PaymentMethod.PhonePay) {
            throw new IllegalArgumentException("Invalid payment method. Must be CreditCard, DebitCard, or Cash.");
        }

        // If status is COMPLETED, validate amount_paid against booking's total_amount
        if (status == Payment.PaymentStatus.Completed) {
            Booking booking = payment.getBooking();
            if (booking == null) {
                throw new IllegalArgumentException("No booking associated with this payment.");
            }

            double totalAmount = booking.getTotalAmount();
            if (amountPaid == null) {
                throw new IllegalArgumentException("Amount paid must be provided.");
            }

            if (Math.abs(amountPaid - totalAmount) > 0.01) { // Allow small floating-point differences
                if (amountPaid < totalAmount) {
                    throw new IllegalArgumentException("Amount is less than required.");
                } else {
                    throw new IllegalArgumentException("Amount is more than required.");
                }
            }
        }

        // Update payment details
        payment.setPaymentStatus(status);
        payment.setPaymentMethod(method);
        if (amountPaid != null) {
            payment.setAmountPaid(amountPaid);
        }
        paymentRepository.save(payment);

        // If payment is completed, update booking status
        if (status == Payment.PaymentStatus.Completed) {
            Booking booking = payment.getBooking();
            booking.setBookingStatus(Booking.BookingStatus.COMPLETED);
            bookingRepository.save(booking);
        }

        return payment;
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Optional<Payment> getPaymentById(Long paymentId) {
        return paymentRepository.findById(paymentId);
    }
}