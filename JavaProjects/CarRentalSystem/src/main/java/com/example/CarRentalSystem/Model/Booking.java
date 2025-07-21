//package com.example.CarRentalSystem.Model;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.fasterxml.jackson.annotation.JsonManagedReference;
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.sql.Timestamp;
//import java.util.Date;
//
//@Data
//@Entity
//@NoArgsConstructor
//@AllArgsConstructor
//public class Booking {
//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "booking_id_seq")
//    @SequenceGenerator(name = "booking_id_seq", sequenceName = "booking_id_seq", initialValue = 123000, allocationSize = 1)
//    private Long bookingId;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id", nullable = false)
//    private User user;
//
//    @ManyToOne
//    @JoinColumn(name = "car_id", nullable = false)
//    private Car car;
//
//    @Temporal(TemporalType.DATE)
//    private Date startDate;
//
//    @Temporal(TemporalType.DATE)
//    private Date endDate;
//
//    @OneToOne(mappedBy = "booking", cascade = CascadeType.ALL, orphanRemoval = true)
//    @JsonManagedReference
//    private Payment payment;
//
//    private double totalAmount;
//
//    @Enumerated(EnumType.STRING)
//    private BookingStatus bookingStatus = BookingStatus.PENDING; // Changed to bookingStatus, default PENDING
//
//    private Timestamp createdAt = new Timestamp(System.currentTimeMillis());
//
//    public enum BookingStatus {
//        PENDING, CONFIRMED, CANCELLED, COMPLETED
//    }
//
//    public Long getBookingId() {
//        return bookingId;
//    }
//
//    public void setBookingId(Long bookingId) {
//        this.bookingId = bookingId;
//    }
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//
//    public Car getCar() {
//        return car;
//    }
//
//    public void setCar(Car car) {
//        this.car = car;
//    }
//
//    public Date getStartDate() {
//        return startDate;
//    }
//
//    public void setStartDate(Date startDate) {
//        this.startDate = startDate;
//    }
//
//    public Date getEndDate() {
//        return endDate;
//    }
//
//    public void setEndDate(Date endDate) {
//        this.endDate = endDate;
//    }
//
//    public Payment getPayment() {
//        return payment;
//    }
//
//    public void setPayment(Payment payment) {
//        this.payment = payment;
//    }
//
//    public double getTotalAmount() {
//        return totalAmount;
//    }
//
//    public void setTotalAmount(double totalAmount) {
//        this.totalAmount = totalAmount;
//    }
//
//    public BookingStatus getBookingStatus() {
//        return bookingStatus;
//    }
//
//    public void setBookingStatus(BookingStatus bookingStatus) {
//        this.bookingStatus = bookingStatus;
//    }
//
//    public Timestamp getCreatedAt() {
//        return createdAt;
//    }
//
//    public void setCreatedAt(Timestamp createdAt) {
//        this.createdAt = createdAt;
//    }
//}

package com.example.CarRentalSystem.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "booking_id_seq")
    @SequenceGenerator(name = "booking_id_seq", sequenceName = "booking_id_seq", initialValue = 123000, allocationSize = 1)
    private Long bookingId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Temporal(TemporalType.DATE)
    private Date endDate;

    @OneToOne(mappedBy = "booking", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Payment payment;

    private double totalAmount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @JsonProperty("status") // Map JSON "status" to "bookingStatus"
    private BookingStatus bookingStatus = BookingStatus.PENDING;

    private Timestamp createdAt = new Timestamp(System.currentTimeMillis());

    public enum BookingStatus {
        PENDING, CONFIRMED, CANCELLED, COMPLETED
    }

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}