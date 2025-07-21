package com.Delivery.FoodDelivery.Controller;


import com.Delivery.FoodDelivery.Model.Restaurant;
import com.Delivery.FoodDelivery.Model.User;
import com.Delivery.FoodDelivery.Service.RestaurantService;
import com.Delivery.FoodDelivery.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("restaurant")
@RestController
public class RestaurantController {
    @Autowired
    RestaurantService restaurantService;

    @Autowired
    UserService userService;


    @PostMapping()
    public ResponseEntity<String> createRestaurant(@RequestBody Restaurant restaurant) {
        // Check if the owner exists and has the role "owner"
        User owner = userService.getUserById(restaurant.getOwner().getUserId());
        if (owner == null || !"Restaurant Owner".equalsIgnoreCase(owner.getRole())) {
            return new ResponseEntity<>("Invalid owner ID. User must have the role 'owner'.", HttpStatus.BAD_REQUEST);
        }

        // Save the restaurant
        Restaurant savedRestaurant = restaurantService.createRestaurant(restaurant);
        return new ResponseEntity<>("Restaurant created successfully with ID: " + savedRestaurant.getRestaurantId(), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<Restaurant>> getAllRestaurantDetails(){
        List<Restaurant> restaurants=restaurantService.getAllRestaurantDetails();
        return  new ResponseEntity<>(restaurants,HttpStatus.FOUND);
    }

    @GetMapping("Id/{restaurantId}")
    public ResponseEntity<Optional<Restaurant>> getRestaurantByUsingID(@PathVariable Long restaurantId){
        Optional<Restaurant> restaurant=restaurantService.getRestaurantById(restaurantId);

        try{
            if(restaurant!=null){
                return new ResponseEntity<>(restaurant,HttpStatus.FOUND);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("name/{restaurantName}")
    public ResponseEntity<Restaurant> getRestaurantByName(@PathVariable String restaurantName){
        Restaurant restaurant=restaurantService.getRestaurantByName(restaurantName);
        if(restaurant!=null)
            return new ResponseEntity<>(restaurant,HttpStatus.FOUND);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("location/{restaurantLocation}")
    public ResponseEntity<Restaurant> getRestaurantByLocation(@PathVariable String restaurantLocation){
        Restaurant restaurant=restaurantService.getRestaurantByLocation(restaurantLocation);
        try {
            if(restaurant!=null){
                return new ResponseEntity<>(restaurant,HttpStatus.FOUND);
            }
            else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("Id/{restaurantId}")
    public ResponseEntity<Restaurant> updateRestaurant(@PathVariable Long restaurantId,@RequestBody Restaurant restaurantDetails){
        Restaurant updateRestaurant=restaurantService.updateRestaurantDetails(restaurantId,restaurantDetails);
        return new ResponseEntity<>(updateRestaurant,HttpStatus.CREATED);
    }


    @DeleteMapping("Id/{restaurantId}")
    public  ResponseEntity<String> deleteRestaurantById(@PathVariable Long restaurantId){
        try{
            if(restaurantService.deleteRestaurantById(restaurantId)){
                return new ResponseEntity<>("Successfully Deleted",HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(restaurantId+" is not There ",HttpStatus.BAD_REQUEST);
            }
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
