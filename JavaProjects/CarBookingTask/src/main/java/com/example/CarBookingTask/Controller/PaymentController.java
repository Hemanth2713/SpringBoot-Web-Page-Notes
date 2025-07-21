package com.example.CarBookingTask.Controller;

import com.example.CarBookingTask.Model.Payment;
import com.example.CarBookingTask.Repository.PaymentRequest;
import com.example.CarBookingTask.Service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/makePayment") // Map the method to POST request at /payment/makePayment
    public String makePayment(@RequestBody PaymentRequest paymentRequest) {
        return paymentService.makePayment(paymentRequest);
    }
}
