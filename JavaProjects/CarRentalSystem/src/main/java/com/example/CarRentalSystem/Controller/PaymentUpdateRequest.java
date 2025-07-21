package com.example.CarRentalSystem.Controller;

import com.example.CarRentalSystem.Model.Payment;

class PaymentUpdateRequest {
    private Payment.PaymentStatus status;
    private Payment.PaymentMethod method;
    private Double amountPaid;

    public Payment.PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(Payment.PaymentStatus status) {
        this.status = status;
    }

    public Payment.PaymentMethod getMethod() {
        return method;
    }

    public void setMethod(Payment.PaymentMethod method) {
        this.method = method;
    }

    public Double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(Double amountPaid) {
        this.amountPaid = amountPaid;
    }
}
