package com.Delivery.FoodDelivery.Repository;


import com.Delivery.FoodDelivery.Model.OrderCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderCartRepository extends JpaRepository<OrderCart,Long> {
}

