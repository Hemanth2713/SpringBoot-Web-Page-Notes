package com.Restaurant.Restaurant.Repository;

import com.Restaurant.Restaurant.Model.AvailabilityStatus;
import com.Restaurant.Restaurant.Model.MenuItem;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem,Long> {

    MenuItem findByItemName( String itemName);

    List<MenuItem> findByAvailability(AvailabilityStatus availabilityStatus);
}
