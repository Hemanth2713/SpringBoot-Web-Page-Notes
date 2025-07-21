package com.Delivery.FoodDelivery.Controller;

import com.Delivery.FoodDelivery.Model.OrderCart;
import com.Delivery.FoodDelivery.Model.OrdersUpdated;
import com.Delivery.FoodDelivery.Service.OrderCartService;
import com.Delivery.FoodDelivery.Service.OrdersUpdatedService;
import com.Delivery.FoodDelivery.Service.RestaurantService;
import com.Delivery.FoodDelivery.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
public class OrdersUpdatedController {

    @Autowired
    private OrdersUpdatedService ordersUpdatedService;

    @Autowired
    private OrderCartService orderCartService;

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;

    // Endpoint to create an order
    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrdersUpdated order) {
        try {
            // Validate user
            if (userService.getUserById(order.getUser().getUserId()) == null) {
                return new ResponseEntity<>("User ID not found", HttpStatus.NOT_FOUND);
            }

            // Validate restaurant
            if (restaurantService.getRestaurantById(order.getRestaurant().getRestaurantId()) == null) {
                return new ResponseEntity<>("Restaurant ID not found", HttpStatus.NOT_FOUND);
            }

            // Handle orderCartIds (fetch corresponding OrderCart entities)
            List<Long> orderCartIds = order.getOrderCartIds();
            if (orderCartIds == null || orderCartIds.isEmpty()) {
                return new ResponseEntity<>("OrderCart List cannot be empty", HttpStatus.BAD_REQUEST);
            }

            // Fetch all OrderCart entities by their IDs
            List<OrderCart> orderCarts = orderCartIds.stream()
                    .map(orderCartService::getOrderCartById)
                    .collect(Collectors.toList());

            // Check if any OrderCart is not found
            if (orderCarts.contains(null)) {
                return new ResponseEntity<>("Invalid OrderCart ID(s)", HttpStatus.NOT_FOUND);
            }

            // Calculate the total amount for the order
            order.calculateTotalAmount(orderCarts);

            // Save the order first to get the orderId
            OrdersUpdated savedOrder = ordersUpdatedService.createOrderDetails(order);

            // Associate OrderCart IDs with the saved order
            orderCartService.saveOrderCartIds(orderCartIds, savedOrder.getOrderId());

            return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error creating order: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Other endpoints as required...
}
