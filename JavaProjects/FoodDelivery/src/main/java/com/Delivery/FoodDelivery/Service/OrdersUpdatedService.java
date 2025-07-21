package com.Delivery.FoodDelivery.Service;

import com.Delivery.FoodDelivery.Model.OrdersUpdated;
import com.Delivery.FoodDelivery.Repository.OrdersUpdatedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrdersUpdatedService {

    @Autowired
    private OrdersUpdatedRepository ordersUpdatedRepository;

    // Method to save OrderUpdated details to the database
    public OrdersUpdated createOrderDetails(OrdersUpdated order) {
        return ordersUpdatedRepository.save(order);
    }

    // Other methods as required
}
