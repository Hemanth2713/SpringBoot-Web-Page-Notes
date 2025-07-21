package com.Restaurant.Restaurant.ServiceImplementation;

import com.Restaurant.Restaurant.DTO.CartItemRequestDto;
import com.Restaurant.Restaurant.DTO.CartItemResponseDto;
import com.Restaurant.Restaurant.Model.Cart;
import com.Restaurant.Restaurant.Model.CartItem;
import com.Restaurant.Restaurant.Model.MenuItem;
import com.Restaurant.Restaurant.Model.User;
import com.Restaurant.Restaurant.Repository.CartItemRepository;
import com.Restaurant.Restaurant.Repository.CartRepository;
import com.Restaurant.Restaurant.Repository.MenuItemRepository;
import com.Restaurant.Restaurant.Service.CartItemService;
import com.Restaurant.Restaurant.utils.CustomUtils;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CartItemServiceImplementation implements CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Autowired
    private CartRepository cartRepository;

    private static final int MAX_QUANTITY = 5;

    @Transactional
    @Override
    public void createCartItem(CartItemRequestDto cartItemRequestDto) {
        User loggedInUser = CustomUtils.getCurrentLoggedInUser();
        log.info("Adding item to cart for user ID: {}", loggedInUser.getUserId());

        // ✅ Changed from Optional check to orElseThrow
        Cart cart = cartRepository.findByUserId(loggedInUser.getUserId())
                .orElseThrow(() -> {
                    log.error("Cart not found for user ID: {}", loggedInUser.getUserId());
                    return new RuntimeException("Cart not found for the current user.");
                });

        // ✅ Menu item fetch with orElseThrow (already correctly done)
        MenuItem menuItem = menuItemRepository.findById(cartItemRequestDto.getMenuItemId())
                .orElseThrow(() -> {
                    log.error("Menu item not found with ID: {}", cartItemRequestDto.getMenuItemId());
                    return new RuntimeException("Menu item not found with ID: " + cartItemRequestDto.getMenuItemId());
                });

        int requestQuantity = cartItemRequestDto.getQuantity();

        // ✅ Check if item already exists in cart
        for (CartItem existingItem : cart.getCartItems()) {
            if (existingItem.getMenuItem().getItemId().equals(cartItemRequestDto.getMenuItemId())) {
                int updatedQuantity = existingItem.getQuantity() + requestQuantity;

                if (updatedQuantity > MAX_QUANTITY) {
                    log.warn("Attempted to exceed max quantity for menu item ID {}", menuItem.getItemId());
                    throw new IllegalArgumentException("Maximum quantity of 5 reached.");
                }

                existingItem.setQuantity(updatedQuantity);
                cartItemRepository.save(existingItem);
                log.info("Updated item quantity in cart for menu item ID {}", menuItem.getItemId());
                return;
            }
        }

        // ✅ Create new cart item
        if (requestQuantity > 0 && requestQuantity <= MAX_QUANTITY) {
            CartItem newCartItem = new CartItem();
            newCartItem.setCart(cart); // ✅ Previously you were passing Optional<Cart>, now it's fixed
            newCartItem.setMenuItem(menuItem);
            newCartItem.setQuantity(requestQuantity);
            cartItemRepository.save(newCartItem);
            log.info("Added new item to cart: menu item ID {}, quantity {}", menuItem.getItemId(), requestQuantity);
        } else {
            log.warn("Invalid quantity: {}. Must be between 1 and 5.", requestQuantity);
            throw new IllegalArgumentException("Quantity must be between 1 and 5.");
        }
    }

    @Override
    public List<CartItemResponseDto> getCartDetailsByCartId() {
        User loggedInUser = CustomUtils.getCurrentLoggedInUser();
        log.info("Fetching cart details for user ID: {}", loggedInUser.getUserId());

        // ✅ Use orElseThrow instead of direct call (avoid potential null)
        Cart cart = cartRepository.findByUserId(loggedInUser.getUserId())
                .orElseThrow(() -> {
                    log.error("Cart not found for user ID: {}", loggedInUser.getUserId());
                    return new RuntimeException("Cart not found for the current user.");
                });

        List<CartItemResponseDto> cartDetails = cartItemRepository.fetchCartDetails(cart.getCartId());
        log.info("Retrieved {} items from cart", cartDetails.size());
        return cartDetails;
    }

    @Override
    public void deleteAllCartItemsByCartId() {
        User loggedInUser = CustomUtils.getCurrentLoggedInUser();
        log.info("Deleting all cart items for user ID: {}", loggedInUser.getUserId());

        // ✅ Use orElseThrow here as well
        Cart cart = cartRepository.findByUserId(loggedInUser.getUserId())
                .orElseThrow(() -> {
                    log.error("Cart not found for user ID: {}", loggedInUser.getUserId());
                    return new RuntimeException("Cart not found for the current user.");
                });

        cartItemRepository.deleteAllCartItemsByCartId(cart.getCartId());
        log.info("All cart items deleted for cart ID: {}", cart.getCartId());
    }
}
