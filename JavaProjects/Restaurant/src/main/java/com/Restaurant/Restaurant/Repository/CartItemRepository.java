package com.Restaurant.Restaurant.Repository;

import com.Restaurant.Restaurant.DTO.CartItemResponseDto;
import com.Restaurant.Restaurant.Model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
//
//    // ✅ [Validated] Using JPQL constructor-based projection (not native SQL)
//    // ✅ [Corrected earlier misunderstanding] Removed nativeQuery=true, switched to JPQL
//    // ✅ [Correct field access] ci.cart.cartId and ci.menuItem.name, price, etc.
//    // ✅ [Projection DTO] Must match CartItemResponseDto constructor
//    @Query("SELECT new com.Restaurant.Restaurant.DTO.CartItemResponseDto(" +
//            "ci.cartItemId, " +                   // ✅ cart item ID
//            "ci.quantity, " +                     // ✅ quantity
//            "(ci.quantity * ci.menuItem.price), " + // ✅ calculated total price
//            "ci.menuItem.itemId, " +              // ✅ menu item ID
//            "ci.menuItem.name, " +                // ✅ menu item name
//            "ci.menuItem.price) " +               // ✅ menu item price
//            "FROM CartItem ci WHERE ci.cart.cartId = :cartId")
//    List<CartItemResponseDto> fetchCartDetails(@Param("cartId") Long cartId); // ✅ @Param properly used

    @Query("SELECT new com.Restaurant.Restaurant.DTO.CartItemResponseDto(" +
            "ci.cartItemId, " +
            "ci.quantity, " +
            "(ci.quantity * ci.menuItem.price), " +
            "ci.menuItem.itemId, " +
            "ci.menuItem.itemName, " +
            "ci.menuItem.price) " +
            "FROM CartItem ci " +
            "WHERE ci.cart.cartId = :cartId")
    List<CartItemResponseDto> fetchCartDetails(@Param("cartId") Long cartId);


    // ✅ [No changes needed] JPQL delete query using @Modifying and @Transactional
    @Modifying
    @Transactional
    @Query("DELETE FROM CartItem ci WHERE ci.cart.cartId = :cartId")
    void deleteAllCartItemsByCartId(@Param("cartId") Long cartId);
}
