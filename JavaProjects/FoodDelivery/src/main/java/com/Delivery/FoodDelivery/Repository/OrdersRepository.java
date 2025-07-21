package com.Delivery.FoodDelivery.Repository;

import com.Delivery.FoodDelivery.Model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {
}
