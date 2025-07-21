package com.Restaurant.Restaurant.ServiceImplementation;

import com.Restaurant.Restaurant.DTO.MenuItemDto;
import com.Restaurant.Restaurant.DTO.OrderItemResponseDTO;
import com.Restaurant.Restaurant.DTO.OrderRequestDTO;
import com.Restaurant.Restaurant.DTO.OrderResponseDTO;
import com.Restaurant.Restaurant.Model.*;
import com.Restaurant.Restaurant.Repository.*;
import com.Restaurant.Restaurant.Service.OrderService;
import com.Restaurant.Restaurant.utils.CustomUtils;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderServiceImplementation implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public String placeOrderFromCart(OrderRequestDTO requestDTO) {
        User loggedInUser = CustomUtils.getCurrentLoggedInUser();

        // 1. Fetch and validate address
        Address address = addressRepository.findById(requestDTO.getAddressId())
                .orElseThrow(() -> new RuntimeException("Address not found with ID: " + requestDTO.getAddressId()));
        address.setUser(loggedInUser);  // Optional - only if needed

        // 2. Fetch user's cart
        Cart cart = cartRepository.findByUserId(loggedInUser.getUserId())
                .orElseThrow(() -> new RuntimeException("Cart not found for user"));

        if (cart.getCartItems() == null || cart.getCartItems().isEmpty()) {
            throw new RuntimeException("Cart is empty. Add items before placing an order.");
        }

        // 3. Create new order
        Order order = Order.builder()
                .user(loggedInUser)
                .address(address)
                .orderTime(LocalDateTime.now())
                .status(OrderStatus.CREATED)
                .build();
        order.setStatus(OrderStatus.CREATED);


        Order savedOrder = orderRepository.save(order);

        // 4. Convert cart items to order items
        List<OrderItem> orderItems = cart.getCartItems().stream().map(cartItem -> {
            return OrderItem.builder()
                    .order(savedOrder)
                    .menuItem(cartItem.getMenuItem())
                    .quantity(cartItem.getQuantity())
                    .itemPrice(cartItem.getMenuItem().getPrice())
                    .build();
        }).toList();

        orderItemRepository.saveAll(orderItems);

        // 5. Calculate and update total amount
        double totalAmount = orderItems.stream()
                .mapToDouble(oi -> oi.getQuantity() * oi.getItemPrice())
                .sum();
        savedOrder.setTotalAmount(totalAmount);
        orderRepository.save(savedOrder);

        // ✅ 6. Clear the cart after placing the order
        cartItemRepository.deleteAll(cart.getCartItems());

        return "Order placed from cart! Order ID: " + savedOrder.getOrderId();
    }

    @Override
    public String cancelOrder(Long orderId) {
        log.info("Attempting to cancel order with ID: {}", orderId);

        // Fetch the order
        Order order = orderRepository.findById(orderId).orElseThrow(() -> {
            log.error("Order not found with ID: {}", orderId);
            throw new RuntimeException("Order not found with ID: " + orderId);
        });

        // Check status
        if (order.getStatus() == OrderStatus.CANCELLED || order.getStatus() == OrderStatus.DELIVERED) {
            log.warn("Order already cancelled or delivered. ID: {}", orderId);
            throw new IllegalArgumentException("Order is already cancelled or delivered and cannot be changed.");
        }

        // Optional business rule: only allow cancelling processed orders
        if (order.getStatus() == OrderStatus.CREATED || order.getStatus() == OrderStatus.PENDING) {
            log.warn("Cannot cancel unprocessed order with ID: {}", orderId);
            throw new IllegalArgumentException("Cannot cancel unprocessed orders.");
        }

        // Cancel the order
        order.setStatus(OrderStatus.CANCELLED);
        orderRepository.save(order);

        log.info("Order cancelled successfully with ID: {}", orderId);
        return "Order cancelled successfully with ID: " + orderId;
    }
    @Override
    public OrderResponseDTO getOrderById(Long orderId) {
        log.info("Fetching order with ID: {}", orderId);

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> {
                    log.error("Order not found with ID: {}", orderId);
                    return new RuntimeException("Order not found with ID: " + orderId);
                });

        // Map the Order to OrderResponseDTO (basic fields + user and address)
        OrderResponseDTO responseDTO = modelMapper.map(order, OrderResponseDTO.class);

        // Map order items to OrderItemResponseDTO
        List<OrderItemResponseDTO> itemDTOs = order.getOrderItems().stream()
                .map(orderItem -> modelMapper.map(orderItem, OrderItemResponseDTO.class))
                .toList();

        responseDTO.setOrderItems(itemDTOs);
        log.info("Order details mapped successfully for ID: {}", orderId);

        return responseDTO;
    }

    @Override
    public List<OrderResponseDTO> getAllOrders() {
        List<Order> orders = orderRepository.findAll();

        return orders.stream().map(order -> {
            // Map Order to OrderResponseDTO
            OrderResponseDTO orderDto = modelMapper.map(order, OrderResponseDTO.class);

            // Fetch OrderItems for each order
            List<OrderItem> orderItems = orderItemRepository.findByOrder_OrderId(order.getOrderId());

            // Map OrderItems to OrderItemResponseDTOs
            List<OrderItemResponseDTO> itemDTOs = orderItems.stream()
                    .map(item -> {
                        OrderItemResponseDTO itemDto = modelMapper.map(item, OrderItemResponseDTO.class);
                        itemDto.setMenuItemDto(modelMapper.map(item.getMenuItem(), MenuItemDto.class)); // Map nested MenuItem
                        return itemDto;
                    })
                    .toList();

            // Set items in order DTO
            orderDto.setOrderItems(itemDTOs);

            return orderDto;
        }).toList();
    }

    @Override
    public List<OrderResponseDTO> getOrdersByUserId(Long userId) {
        User loggedInUser = CustomUtils.getCurrentLoggedInUser();

        // Only allow user to view their own orders unless they're an admin
        if (loggedInUser.getRole().name().equals("USER") && !loggedInUser.getUserId().equals(userId)) {
            log.warn("Unauthorized access attempt by user {} to orders of user {}", loggedInUser.getUserId(), userId);
            throw new IllegalArgumentException("You are not authorized to view orders of another user.");
        }

        List<Order> orders = orderRepository.findByUser_UserId(userId); // ✅ Correct method

        return orders.stream().map(order -> {
            // Map order to response DTO
            OrderResponseDTO orderDto = modelMapper.map(order, OrderResponseDTO.class);

            // Fetch and map order items
            List<OrderItem> orderItems = orderItemRepository.findByOrder_OrderId(order.getOrderId());

            List<OrderItemResponseDTO> itemDTOs = orderItems.stream()
                    .map(item -> {
                        OrderItemResponseDTO itemDto = modelMapper.map(item, OrderItemResponseDTO.class);
                        itemDto.setMenuItemDto(modelMapper.map(item.getMenuItem(), MenuItemDto.class));
                        return itemDto;
                    })
                    .toList();

            orderDto.setOrderItems(itemDTOs);
            return orderDto;
        }).toList();
    }


    @Override
    public OrderResponseDTO updateOrderStatus(Long orderId, OrderStatus status) {
        log.info("Updating order status for ID: {} to {}", orderId, status);

        // Fetch the order
        Order order = orderRepository.findById(orderId).orElseThrow(() -> {
            log.error("Order not found with ID: {}", orderId);
            throw new RuntimeException("Order not found with id: " + orderId);
        });

        // Update and save order status
        order.setStatus(status);
        Order updatedOrder = orderRepository.save(order);

        // Map Order to DTO
        OrderResponseDTO orderDto = modelMapper.map(updatedOrder, OrderResponseDTO.class);

        // Map OrderItems to OrderItemResponseDTO
        List<OrderItem> orderItems = orderItemRepository.findByOrder_OrderId(orderId);
        List<OrderItemResponseDTO> itemDTOs = orderItems.stream()
                .map(item -> {
                    OrderItemResponseDTO itemDto = modelMapper.map(item, OrderItemResponseDTO.class);
                    itemDto.setMenuItemDto(modelMapper.map(item.getMenuItem(), MenuItemDto.class)); // map nested menu item
                    return itemDto;
                })
                .toList();

        // Attach items to order DTO
        orderDto.setOrderItems(itemDTOs);

        log.info("Order status updated and mapped successfully for ID: {}", orderId);

        return orderDto;
    }




}
