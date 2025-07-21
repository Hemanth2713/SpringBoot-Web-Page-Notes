package com.Restaurant.Restaurant.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CartItemResponseDto {
    private Long cartItemId;
    private Integer quantity;
    private Double totalPrice;
    private Long itemId;
    private String name;
    private Double price;

    // Getters and setters
}