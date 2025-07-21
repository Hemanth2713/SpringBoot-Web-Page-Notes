package com.example.CarRentalSystem.Controller;

import com.example.CarRentalSystem.Model.Payment;
import com.example.CarRentalSystem.Service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PutMapping("/{paymentId}/status")
    public ResponseEntity<?> updatePaymentStatus(
            @PathVariable Long paymentId,
            @RequestBody PaymentUpdateRequest request) {
        try {
            Payment updatedPayment = paymentService.updatePaymentStatus(
                    paymentId,
                    request.getStatus(),
                    request.getMethod(),
                    request.getAmountPaid()
            );
            return ResponseEntity.ok(updatedPayment);
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Payment>> getAllPayments() {
        List<Payment> payments = paymentService.getAllPayments();
        return ResponseEntity.ok(payments);
    }

    @GetMapping("/{paymentId}")
    public ResponseEntity<?> getPaymentById(@PathVariable Long paymentId) {
        Optional<Payment> payment = paymentService.getPaymentById(paymentId);
        return payment.isPresent()
                ? ResponseEntity.ok(payment.get())
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Payment not found");
    }
}

