package com.Restaurant.Restaurant.Repository;

import com.Restaurant.Restaurant.Model.Order;
import lombok.Lombok;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser_UserId(Long userId); // ✅ Correct!
}
