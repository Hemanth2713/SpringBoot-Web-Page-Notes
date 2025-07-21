package com.Delivery.FoodDelivery.Controller;

import com.Delivery.FoodDelivery.Model.Delivery;
import com.Delivery.FoodDelivery.Service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("delivery")
@RestController
public class DeliveryController {

    @Autowired
    DeliveryService deliveryService;
    @PostMapping()
    public ResponseEntity<Delivery> addDeliveryDetails(@RequestBody Delivery delivery){
        return new ResponseEntity<>(deliveryService.addDeliveryDetails(delivery), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<Delivery>> displayAllDeliveryDetails(){
        List<Delivery> deliveryList=deliveryService.getAllDeliveryDetails();
        return new ResponseEntity<>(deliveryList,HttpStatus.FOUND);
    }

    @GetMapping("Id/{deliveryId}")
    public ResponseEntity<Delivery> getDeliveryDetailsById(@PathVariable Long deliveryId){
        return new ResponseEntity<>(deliveryService.getDeliveryDetailsById(deliveryId),HttpStatus.FOUND);
    }

    @PutMapping("Id/{deliveryID}")
    public ResponseEntity<Delivery> updateDeliveryStatus(@PathVariable Long deliveryID,@RequestBody Delivery deliveryDetails){
        Delivery delivery=deliveryService.updateDeliveryStatus(deliveryID,deliveryDetails);
        return new ResponseEntity<>(delivery,HttpStatus.CREATED);
    }

    @DeleteMapping("Id/{deliveryId}")
    public ResponseEntity<String> deleteDeliveryById(@PathVariable("deliveryId") Long id){
        try{
            if(deliveryService.deleteUserById(id)){
                return new ResponseEntity<>("Successfully Deleted",HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(id+" Not Found",HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
