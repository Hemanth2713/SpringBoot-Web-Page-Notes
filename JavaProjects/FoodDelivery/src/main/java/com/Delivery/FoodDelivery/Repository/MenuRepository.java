package com.Delivery.FoodDelivery.Repository;


import com.Delivery.FoodDelivery.Model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
    @Query("SELECT m FROM Menu m WHERE LOWER(m.itemName) LIKE LOWER(CONCAT('%', :itemName, '%'))")
    List<Menu> findByItemNameContainingIgnoreCase(@Param("itemName") String itemName);

    List<Menu> findByAvailability(boolean itemAvailability);


}