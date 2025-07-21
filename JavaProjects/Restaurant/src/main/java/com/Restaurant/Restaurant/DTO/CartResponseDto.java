package com.Restaurant.Restaurant.DTO;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartResponseDto {
    private Integer cartId;
    private Integer userId;
    private Integer itemId;
    private String itemName;
    private Integer quantity;
    private BigDecimal price;
}
