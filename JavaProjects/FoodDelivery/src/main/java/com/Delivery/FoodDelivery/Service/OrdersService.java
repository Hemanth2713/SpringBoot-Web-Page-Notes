package com.Delivery.FoodDelivery.Service;

import com.Delivery.FoodDelivery.Model.Orders;
import com.Delivery.FoodDelivery.Repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class OrdersService {
    @Autowired
    OrdersRepository ordersRepository;

    public Orders createOrderDetails(Orders orders) {
        return ordersRepository.save(orders);
    }

    public List<Orders> getAllOderDetails() {
        return ordersRepository.findAll();
    }

    public Orders getOrderById(Long orderId) {
        return ordersRepository.findById(orderId).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,orderId+" was Not Found"));
    }

    public Orders updateOrderDetails(Long orderId, Orders orders) {
        Orders existingOrders=ordersRepository.findById(orderId).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,orderId+"is Not Found"));
        existingOrders.setOrderId(existingOrders.getOrderId());
        existingOrders.setOrderTime(existingOrders.getOrderTime());
        existingOrders.setTotalAmount(existingOrders.getTotalAmount());
        existingOrders.setUser(existingOrders.getUser());


        existingOrders.setStatus(orders.getStatus());

        return ordersRepository.save(existingOrders);
    }

    public boolean deleteById(Long id) {
        if(ordersRepository.findById(id).isPresent()){
            ordersRepository.deleteById(id);
            return true;
        }
        return  false;
    }
}