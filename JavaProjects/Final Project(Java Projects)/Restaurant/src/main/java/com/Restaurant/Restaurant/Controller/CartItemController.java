package com.Restaurant.Restaurant.Controller;

import com.Restaurant.Restaurant.DTO.CartItemRequestDto;
import com.Restaurant.Restaurant.DTO.CartItemResponseDto;
import com.Restaurant.Restaurant.DTO.CartResponseDto;
import com.Restaurant.Restaurant.Service.CartItemService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/cart-items")
public class CartItemController {

    @Autowired
    private CartItemService cartItemService;

    @PostMapping
    public ResponseEntity<String> createCartItem(@Valid @RequestBody CartItemRequestDto cartItemRequestDto) {
        log.info("Received request to add item to cart: {}", cartItemRequestDto);
        cartItemService.createCartItem(cartItemRequestDto);
        log.info("Item successfully added to cart.");

        String message = String.format("Added new item to cart: menu item ID %d, quantity %d",
                cartItemRequestDto.getItemId(), cartItemRequestDto.getQuantity());

        return new ResponseEntity<>(message, HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<List<CartResponseDto>> getCartDetails() {
        log.info("Fetching cart details for current user.");
        List<CartResponseDto> response = cartItemService.getCartDetailsByCartId();
        log.info("Fetched {} items from cart.", response.size());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAllCartItems() {
        log.info("Request received to delete all cart items for current user.");
        cartItemService.deleteAllCartItemsByCartId();
        log.info("All cart items deleted successfully.");
        return ResponseEntity.ok("All cart items deleted successfully.");
    }
}
