package com.Restaurant.Restaurant.DTO;

import lombok.Data;

@Data
public class OrderItemRequestDTO {
    private Long itemId;
    private Integer quantity;
}