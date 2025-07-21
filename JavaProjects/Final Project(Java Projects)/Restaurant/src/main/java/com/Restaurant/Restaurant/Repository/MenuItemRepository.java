package com.Restaurant.Restaurant.Repository;

import com.Restaurant.Restaurant.Model.AvailabilityStatus;
import com.Restaurant.Restaurant.Model.MenuItem;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem,Long> {

    MenuItem findByItemName( String itemName);

    List<MenuItem> findByAvailability(AvailabilityStatus availabilityStatus);


    @Query("SELECT m FROM MenuItem m WHERE m.itemId = :itemId")
    Optional<MenuItem> findByItemId(@Param("itemId") Long itemId);
}
