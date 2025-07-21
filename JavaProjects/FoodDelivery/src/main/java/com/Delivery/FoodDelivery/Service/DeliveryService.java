package com.Delivery.FoodDelivery.Service;

import com.Delivery.FoodDelivery.Model.Delivery;
import com.Delivery.FoodDelivery.Repository.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class DeliveryService {
    @Autowired
    DeliveryRepository deliveryRepository;
    public Delivery addDeliveryDetails(Delivery delivery) {
        return deliveryRepository.save(delivery);
    }

    public List<Delivery> getAllDeliveryDetails() {
        return deliveryRepository.findAll();
    }

    public Delivery getDeliveryDetailsById(Long deliveryId) {
        return deliveryRepository.findById(deliveryId).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Delivery Id is Not Found"+deliveryId));
    }

    public Delivery updateDeliveryStatus(Long deliveryID, Delivery deliveryDetails) {
        Delivery existingDelivery=deliveryRepository.findById(deliveryID).orElseThrow(()->new  RuntimeException("Delivery Id is  not Found"+deliveryID));
        existingDelivery.setDeliveryId(existingDelivery.getDeliveryId());
        existingDelivery.setDeliveryPersonId(existingDelivery.getDeliveryPersonId());
        existingDelivery.setOrderId(existingDelivery.getOrderId());
        existingDelivery.setStatus(deliveryDetails.getStatus());
        existingDelivery.setTrackingInfo(existingDelivery.getTrackingInfo());
        return deliveryRepository.save(existingDelivery);

    }

    public boolean deleteUserById(Long id) {
        if(deliveryRepository.findById(id).isPresent()){
            deliveryRepository.deleteById(id);
            return true;
        }
        return false;
    }
}