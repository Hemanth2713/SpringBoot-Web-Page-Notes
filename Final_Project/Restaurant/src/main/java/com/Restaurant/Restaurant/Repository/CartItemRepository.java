package com.Restaurant.Restaurant.Repository;


import com.Restaurant.Restaurant.DTO.CartResponseDto;
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

    @Query(value = """
    SELECT 
        c.cart_id AS cartId,
        u.user_id AS userId,
        mi.item_id AS itemId,
        mi.item_name AS itemName,
        ci.quantity AS quantity,
        mi.price AS price
    FROM 
        cart_item ci
    JOIN 
        order_cart c ON ci.cart_id = c.cart_id
    JOIN 
        menu_item mi ON ci.item_id = mi.item_id
    JOIN 
        users u ON c.user_id = u.user_id
    WHERE 
        c.cart_id = :cartId
""", nativeQuery = true)
    List<CartResponseDto> fetchCartDetails(@Param("cartId") Long cartId);


    // ✅ [No changes needed] JPQL delete query using @Modifying and @Transactional
    @Modifying
    @Transactional
    @Query("DELETE FROM CartItem ci WHERE ci.cart.cartId = :cartId")
    void deleteAllCartItemsByCartId(@Param("cartId") Long cartId);
}
