package com.example.CarRentalSystem.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payment_id_seq")
    @SequenceGenerator(name = "payment_id_seq", sequenceName = "payment_id_seq", initialValue = 1111000, allocationSize = 1)
    private Long paymentId;

    @OneToOne
    @JoinColumn(name = "booking_id", nullable = false)
    @JsonBackReference  // Prevents infinite recursion
    private Booking booking;

    private double amountPaid;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentMethod paymentMethod; // Required, must be selected

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus = PaymentStatus.Pending; // Default to Pending

    @Column(nullable = false, updatable = false)
    private Timestamp paymentDate;

    @PrePersist
    public void setPaymentDateBeforeInsert() {
        this.paymentDate = new Timestamp(System.currentTimeMillis()); // Auto-set payment date on creation
    }

    public enum PaymentMethod {
        None, CreditCard, DebitCard, PhonePay, Cash
    }


    public enum PaymentStatus {
        Pending, Completed, Failed
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Timestamp getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Timestamp paymentDate) {
        this.paymentDate = paymentDate;
    }
}
