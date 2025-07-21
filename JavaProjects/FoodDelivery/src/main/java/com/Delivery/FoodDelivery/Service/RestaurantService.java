package com.Delivery.FoodDelivery.Service;

import com.Delivery.FoodDelivery.Model.Restaurant;
import com.Delivery.FoodDelivery.Model.User;
import com.Delivery.FoodDelivery.Repository.RestaurantRepository;
import com.Delivery.FoodDelivery.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {


    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    UserRepository userRepository;

    public Restaurant createRestaurant(Restaurant restaurant) {
        // Validate owner ID
        User owner = userRepository.findById(restaurant.getOwner().getUserId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid owner ID."));

        if (!"Restaurant Owner".equalsIgnoreCase(owner.getRole())) {
            throw new IllegalArgumentException("The user is not an owner.");
        }

        // Save the restaurant
        return restaurantRepository.save(restaurant);
    }


    public List<Restaurant> getAllRestaurantDetails() {
        return restaurantRepository.findAll();
    }

    public Optional<Restaurant> getRestaurantById(Long restaurantId) {
        return Optional.ofNullable(restaurantRepository.findById(restaurantId).orElse(null));
    }

    public Restaurant getRestaurantByName(String restaurantName) {
        return restaurantRepository.findByRestaurantName(restaurantName).orElse(null);
    }

    public Restaurant getRestaurantByLocation(String restaurantLocation) {
        return restaurantRepository.findByLocation(restaurantLocation);
    }

    public boolean deleteRestaurantById(Long restaurantId) {
        if(restaurantRepository.findById(restaurantId).isPresent()){
            restaurantRepository.deleteById(restaurantId);
            return true;
        }
        else {
            return false;
        }
    }

    public Restaurant updateRestaurantDetails(Long restaurantId, Restaurant restaurantDetails) {
        Restaurant existingRestaurant=restaurantRepository.findById(restaurantId).orElseThrow(()->new  RuntimeException("Restaurant not Found"));
        existingRestaurant.setRestaurantName(restaurantDetails.getRestaurantName());
        existingRestaurant.setLocation(restaurantDetails.getLocation());
        existingRestaurant.setRating(restaurantDetails.getRating());
        existingRestaurant.setOwner(restaurantDetails.getOwner());
        return restaurantRepository.save(existingRestaurant);
    }


}
