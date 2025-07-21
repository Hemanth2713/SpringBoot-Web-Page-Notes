package com.Restaurant.Restaurant.Service;

import com.Restaurant.Restaurant.DTO.CartItemRequestDto;
import com.Restaurant.Restaurant.DTO.CartItemResponseDto;
import com.Restaurant.Restaurant.DTO.CartResponseDto;
import jakarta.validation.Valid;

import java.util.List;

public interface CartItemService {
    void createCartItem(@Valid CartItemRequestDto cartItemRequestDto);

    List<CartResponseDto> getCartDetailsByCartId();

    void deleteAllCartItemsByCartId();
}
