package com.Restaurant.Restaurant.DTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


import com.Restaurant.Restaurant.Model.OrderStatus;
import lombok.Data;

@Data
public class OrderResponseDTO {
    private Long orderId;
    private UserResponseDto userDto;
    private AddressDto addressDto;
    private BigDecimal totalPrice;
    private OrderStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<OrderItemResponseDTO> orderItems;
}