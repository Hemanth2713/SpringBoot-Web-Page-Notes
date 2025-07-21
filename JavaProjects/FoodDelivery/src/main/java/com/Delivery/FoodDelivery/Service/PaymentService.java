package com.Delivery.FoodDelivery.Service;

import com.Delivery.FoodDelivery.Model.Payment;
import com.Delivery.FoodDelivery.Model.Orders;
import com.Delivery.FoodDelivery.Repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private OrdersService ordersService;

    public Payment createPayment(Long orderId, Double amount, String paymentMethod, String paymentStatus) {
        Orders order = ordersService.getOrderById(orderId);
        if (order == null) {
            throw new RuntimeException("Order not found with ID: " + orderId);
        }

        Payment payment = new Payment(order, amount, paymentMethod, paymentStatus);
        return paymentRepository.save(payment);
    }

    public Payment getPaymentById(Long paymentId) {
        Optional<Payment> payment = paymentRepository.findById(paymentId);
        return payment.orElseThrow(() -> new RuntimeException("Payment not found with ID: " + paymentId));
    }
}
