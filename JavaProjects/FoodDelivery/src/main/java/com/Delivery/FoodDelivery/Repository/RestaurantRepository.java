package com.Delivery.FoodDelivery.Repository;

import com.Delivery.FoodDelivery.Model.Restaurant;
import lombok.Lombok;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant,Long> {

    Restaurant findByLocation(String restaurantLocation);

    Optional<Restaurant> findByRestaurantName(String restaurantName);
}
