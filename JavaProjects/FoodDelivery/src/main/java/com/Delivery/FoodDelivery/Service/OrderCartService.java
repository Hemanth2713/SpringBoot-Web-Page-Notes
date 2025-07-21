package com.Delivery.FoodDelivery.Service;

import com.Delivery.FoodDelivery.Model.OrderCart;
import com.Delivery.FoodDelivery.Repository.OrderCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class OrderCartService {
    @Autowired
    OrderCartRepository orderCartRepository;

    public OrderCart createOrderCart(OrderCart orderCart) {
        return orderCartRepository.save(orderCart);
    }

    public List<OrderCart> getAllOrderCartDetails() {
        return orderCartRepository.findAll();
    }

    public OrderCart getOrderCartById(Long id) {
        return orderCartRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"OrderCartDetails not found with ID : "+id));

    }

    public OrderCart updateOrderCartById(Long id, OrderCart orderCart) {
        OrderCart existingOrderCart=orderCartRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"OrderCart Details Not Found By the OrderCartId"+id));
        existingOrderCart.setOrderCartId(existingOrderCart.getOrderCartId());
        existingOrderCart.setMenu(orderCart.getMenu());
        existingOrderCart.setQuantity(orderCart.getQuantity());
        existingOrderCart.setPrice(orderCart.getPrice());

        return orderCartRepository.save(existingOrderCart);
    }


    public boolean deleteById(Long menuId) {
        return false;
    }

    public void saveOrderCartIds(List<Long> orderCartIds, Long orderId) {
    }
}