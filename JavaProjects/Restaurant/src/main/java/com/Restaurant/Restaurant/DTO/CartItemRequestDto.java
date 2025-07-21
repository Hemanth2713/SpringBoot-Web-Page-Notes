package com.Restaurant.Restaurant.DTO;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItemRequestDto {

    private Integer quantity;
    private Long menuItemId;  // ID of the selected menu item
}
