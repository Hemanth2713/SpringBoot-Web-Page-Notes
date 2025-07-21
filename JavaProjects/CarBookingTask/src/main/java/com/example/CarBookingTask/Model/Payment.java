package com.example.CarBookingTask.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity

public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    @ManyToOne
    @JoinColumn(name = "bookingId", nullable = false)
    private Booking booking;

    @ManyToOne
    @JoinColumn(name = "customerId", nullable = false)
    private Customer customer;

    private Double paymentAmount;
    private LocalDateTime paymentDateTime;
    private String paymentStatus; // e.g., Pending, Completed, Failed, Refunded
    private String paymentMethod; // e.g., Credit Card, Debit Card, PayPal
    private String currency = "USD"; // Default is USD
//    private String transactionReference;
//    private String gatewayProvider;
//    private Double gatewayFee;
//    private Double discountApplied;
//    private Double taxAmount;
//    private String refundDetails;
//    private String installmentDetails;

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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(Double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public LocalDateTime getPaymentDateTime() {
        return paymentDateTime;
    }

    public void setPaymentDateTime(LocalDateTime paymentDateTime) {
        this.paymentDateTime = paymentDateTime;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
//
//    public String getTransactionReference() {
//        return transactionReference;
//    }
//
//    public void setTransactionReference(String transactionReference) {
//        this.transactionReference = transactionReference;
//    }
//
//    public String getGatewayProvider() {
//        return gatewayProvider;
//    }
//
//    public void setGatewayProvider(String gatewayProvider) {
//        this.gatewayProvider = gatewayProvider;
//    }
//
//    public Double getGatewayFee() {
//        return gatewayFee;
//    }
//
//    public void setGatewayFee(Double gatewayFee) {
//        this.gatewayFee = gatewayFee;
//    }
//
//    public Double getDiscountApplied() {
//        return discountApplied;
//    }
//
//    public void setDiscountApplied(Double discountApplied) {
//        this.discountApplied = discountApplied;
//    }
//
//    public Double getTaxAmount() {
//        return taxAmount;
//    }
//
//    public void setTaxAmount(Double taxAmount) {
//        this.taxAmount = taxAmount;
//    }
//
//    public String getRefundDetails() {
//        return refundDetails;
//    }
//
//    public void setRefundDetails(String refundDetails) {
//        this.refundDetails = refundDetails;
//    }
//
//    public String getInstallmentDetails() {
//        return installmentDetails;
//    }
//
//    public void setInstallmentDetails(String installmentDetails) {
//        this.installmentDetails = installmentDetails;
//    }

    public void setPaymentDate(LocalDateTime now) {
    }

    // Lombok's @Data handles getters, setters, and other boilerplate code
}
