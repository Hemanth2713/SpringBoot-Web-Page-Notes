package com.Restaurant.Restaurant.Controller;

import com.Restaurant.Restaurant.DTO.OrderRequestDTO;
import com.Restaurant.Restaurant.DTO.OrderResponseDTO;
import com.Restaurant.Restaurant.Model.OrderStatus;
import com.Restaurant.Restaurant.Model.User;
import com.Restaurant.Restaurant.Repository.UserRepository;
import com.Restaurant.Restaurant.Service.OrderService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<String> placeOrderFromCart(@Valid @RequestBody OrderRequestDTO orderRequestDTO) {
        log.info("Received order request: {}", orderRequestDTO);

        try {
            String confirmationMessage = orderService.placeOrderFromCart(orderRequestDTO);
            log.info("Order placed successfully.");
            return ResponseEntity.ok(confirmationMessage);

        } catch (RuntimeException e) {
            log.error("Error while placing order: {}", e.getMessage());
            return ResponseEntity.badRequest().body("Failed to place order: " + e.getMessage());

        } catch (Exception e) {
            log.error("Unexpected error: ", e);
            return ResponseEntity.internalServerError().body("An unexpected error occurred.");
        }
    }
    @PostMapping("/cancel")
    public ResponseEntity<String> cancelOrder(@RequestParam Long orderId) {
        log.info("Received cancel request for Order ID: {}", orderId);

        try {
            String result = orderService.cancelOrder(orderId);
            log.info("Order cancelled successfully: {}", result);
            return ResponseEntity.ok(result);

        } catch (RuntimeException e) {
            log.error("Failed to cancel order: {}", e.getMessage());
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());

        } catch (Exception e) {
            log.error("Unexpected error while canceling order", e);
            return ResponseEntity.internalServerError().body("An unexpected error occurred.");
        }
    }


    @GetMapping("/getOrderById/{orderId}")
    public ResponseEntity<OrderResponseDTO> getOrderById(@PathVariable Long orderId) {
        log.info("Fetching order with ID: {}", orderId);
        OrderResponseDTO order = orderService.getOrderById(orderId);
        log.info("Order fetched successfully: {}", orderId);
        return new ResponseEntity<>(order, HttpStatus.FOUND);
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<OrderResponseDTO>> getAllOrders() {
        log.info("Fetching all orders");
        List<OrderResponseDTO> orders = orderService.getAllOrders();
        log.info("Fetched {} orders", orders.size());
        return new ResponseEntity<>(orders,HttpStatus.FOUND);
    }

    @GetMapping("/ordersByUserId/{userId}")
    public ResponseEntity<List<OrderResponseDTO>> getOrdersByUserId(@PathVariable Long userId) {
        log.info("Fetching orders for user ID: {}", userId);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> {
                    log.error("User not found with ID: {}", userId);
                    return new RuntimeException("User not found with ID: " + userId);
                });

        List<OrderResponseDTO> userOrders = orderService.getOrdersByUserId(user.getUserId());
        log.info("Fetched {} orders for user ID: {}", userOrders.size(), userId);
        return new ResponseEntity<>(userOrders,HttpStatus.FOUND);
    }
    @PatchMapping("/updateStatus/{orderId}")
    public ResponseEntity<OrderResponseDTO> updateOrderStatus(
            @PathVariable Long orderId,
            @RequestParam OrderStatus status) {

        log.info("Updating status for order ID: {} to {}", orderId, status);

        OrderResponseDTO updatedOrder = orderService.updateOrderStatus(orderId, status);

        log.info("Order status updated successfully for ID: {}", orderId);
        return new ResponseEntity<>(updatedOrder,HttpStatus.CREATED);

    }

}
