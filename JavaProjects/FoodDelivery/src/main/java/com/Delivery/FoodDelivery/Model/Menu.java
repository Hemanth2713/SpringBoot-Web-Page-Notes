package com.Delivery.FoodDelivery.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "menu_id_seq")
    @SequenceGenerator(name = "menu_id_seq",sequenceName = "menu_id_seq",initialValue = 101,allocationSize = 1)
    private Long menuId;
    @ManyToOne
    @JoinColumn(name="restaurant_id",nullable = false)
    @JsonBackReference
    private Restaurant restaurant;
    private String itemName;
    private double price;
    private String description;
    private boolean availability;


    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public List<OrderCart> getOrderCartList() {
        return orderCartList;
    }

    public void setOrderCartList(List<OrderCart> orderCartList) {
        this.orderCartList = orderCartList;
    }

    @OneToMany(mappedBy = "menu",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<OrderCart> orderCartList;
}
