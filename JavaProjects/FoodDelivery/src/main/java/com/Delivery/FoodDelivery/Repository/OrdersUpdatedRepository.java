package com.Delivery.FoodDelivery.Repository;

import com.Delivery.FoodDelivery.Model.OrdersUpdated;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersUpdatedRepository extends JpaRepository<OrdersUpdated, Long> {
    // You can add custom query methods here if needed
}
