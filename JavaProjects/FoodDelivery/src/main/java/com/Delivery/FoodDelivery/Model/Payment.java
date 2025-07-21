package com.Delivery.FoodDelivery.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payment_id_seq")
    @SequenceGenerator(name = "payment_id_seq", sequenceName = "payment_id_seq", initialValue = 2000, allocationSize = 1)
    private Long paymentId;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Orders order;

    private Double amount;

    private String paymentMethod; // Example: "Credit Card", "PayPal", "Cash"

    private String paymentStatus; // Example: "Pending", "Completed", "Failed"

    private LocalDateTime paymentDate;

    public Payment(Orders order, Double amount, String paymentMethod, String paymentStatus) {
        this.order = order;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
        this.paymentDate = LocalDateTime.now();
    }
}
