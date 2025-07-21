package com.Restaurant.Restaurant.DTO;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class OrderItemResponseDTO {
    private Long orderItemId;
    private MenuItemDto menuItemDto;
    private Integer quantity;
    private BigDecimal price;
}