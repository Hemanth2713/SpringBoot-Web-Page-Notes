package com.Restaurant.Restaurant.DTO;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartResponseDto {
    private Long cartId;
    private Long userId;
    private Long itemId;
    private String itemName;
    private Integer quantity;
    private Double price;
}
