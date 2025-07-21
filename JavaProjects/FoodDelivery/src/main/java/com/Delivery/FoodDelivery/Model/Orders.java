package com.Delivery.FoodDelivery.Model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalTime;
import java.util.List;

@Data
@Entity
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_id_seq")
    @SequenceGenerator(name = "order_id_seq", sequenceName = "order_id_seq", initialValue = 123000, allocationSize = 1)
    private Long orderId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    private String status;
    private Double totalAmount;

    @Column(columnDefinition = "TIME")
    private LocalTime orderTime;

    // Store only the OrderCart IDs instead of full OrderCart objects
    @ElementCollection
    @CollectionTable(name = "order_cart_ids", joinColumns = @JoinColumn(name = "order_id"))
    @Column(name = "order_cart_id")
    private List<Long> orderCartIds;

    // Calculate the total amount for the order based on the orderCartIds
    public void calculateTotalAmount(List<OrderCart> orderCarts) {
        this.totalAmount = orderCarts.stream()
                .mapToDouble(OrderCart::getPrice)
                .sum();
    }

    // Getters and setters (already provided in the original code)
    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public LocalTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalTime orderTime) {
        this.orderTime = orderTime;
    }

    public List<Long> getOrderCartIds() {
        return orderCartIds;
    }

    public void setOrderCartIds(List<Long> orderCartIds) {
        this.orderCartIds = orderCartIds;
    }
}
