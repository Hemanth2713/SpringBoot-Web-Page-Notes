package com.Restaurant.Restaurant.Service;

import com.Restaurant.Restaurant.DTO.OrderRequestDTO;
import com.Restaurant.Restaurant.DTO.OrderResponseDTO;
import com.Restaurant.Restaurant.Model.OrderStatus;
import jakarta.validation.Valid;

import java.util.List;

public interface OrderService {
    String placeOrderFromCart(@Valid OrderRequestDTO orderRequestDTO);

    String cancelOrder(Long orderId);

    OrderResponseDTO getOrderById(Long orderId);

    List<OrderResponseDTO> getAllOrders();

    OrderResponseDTO updateOrderStatus(Long orderId, OrderStatus status);

    List<OrderResponseDTO> getOrdersByUserId(Long userId);
}
