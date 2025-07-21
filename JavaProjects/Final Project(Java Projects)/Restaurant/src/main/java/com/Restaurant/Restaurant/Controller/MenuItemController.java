package com.Restaurant.Restaurant.Controller;

import com.Restaurant.Restaurant.DTO.MenuItemDto;
import com.Restaurant.Restaurant.Model.AvailabilityStatus;
import com.Restaurant.Restaurant.Service.MenuItemService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/menuItem")
public class MenuItemController {

    @Autowired
    private MenuItemService menuItemService;

    // Create
    @PostMapping
    public ResponseEntity<String> createMenuItem(@Valid @RequestBody MenuItemDto menuItemDto) {
        log.info("Creating new menu item: {}", menuItemDto);
        menuItemService.createMenu(menuItemDto);
        log.info("Menu item created successfully "+menuItemDto.getItemName());
        return new ResponseEntity<>("Menu item created successfully "+menuItemDto.getItemName(), HttpStatus.CREATED);
    }

    // Read all
    @GetMapping
    public ResponseEntity<List<MenuItemDto>> getAllMenuItems() {
        log.info("Fetching all menu items");
        List<MenuItemDto> menuItems = menuItemService.getAllMenuItems();
        log.info("Fetched {} menu items", menuItems.size());
        return new ResponseEntity<>(menuItems, HttpStatus.OK);
    }

    // Read by ID
    @GetMapping("/{id}")
    public ResponseEntity<MenuItemDto> getMenuItem(@PathVariable Long id) {
        log.info("Fetching menu item by ID: {}", id);
        MenuItemDto menuItem = menuItemService.getMenuItemById(id);
        log.info("Menu item found: {}", menuItem);
        return new ResponseEntity<>(menuItem, HttpStatus.OK);
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<String> updateMenuItem(@PathVariable Long id,
                                                 @Valid @RequestBody MenuItemDto menuItemDto) {
        log.info("Updating menu item ID {}: {}", id, menuItemDto);
        menuItemService.updateMenuItem(id, menuItemDto);
        log.info("Menu item updated successfully for ID {}", id);
        return new ResponseEntity<>("Menu item updated successfully.", HttpStatus.OK);
    }

    // Toggle availability
    @PutMapping("/{id}/toggle-availability")
    public ResponseEntity<String> toggleAvailabilityStatus(@PathVariable Long id) {
        log.info("Toggling availability status of menu item ID {}", id);
        menuItemService.toggleAvailabilityStatus(id);
        log.info("Availability status toggled successfully for menu item ID {}", id);
        return new ResponseEntity<>("Menu item availability status toggled successfully", HttpStatus.OK);
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMenuItem(@PathVariable Long id) {
        log.info("Deleting menu item ID {}", id);
        menuItemService.deleteMenuItem(id);
        log.info("Menu item deleted successfully for ID {}", id);
        return new ResponseEntity<>("Menu item deleted successfully.", HttpStatus.OK);
    }

    // Update availability status directly
//    @PatchMapping("/{id}/availability")
//    public ResponseEntity<String> updateAvailability(@PathVariable Long id,
//                                                     @RequestParam AvailabilityStatus status) {
//        log.info("Updating availability status of menu item ID {} to {}", id, status);
//        menuItemService.updateAvailabilityStatus(id, status);
//        log.info("Availability status updated successfully for menu item ID {}", id);
//        return new ResponseEntity<>("Menu item availability updated to " + status + ".", HttpStatus.OK);
//    }

    // Get only available items
    @GetMapping("/available")
    public ResponseEntity<List<MenuItemDto>> getAvailableMenuItems() {
        log.info("Fetching available menu items");
        List<MenuItemDto> availableItems = menuItemService.getAvailableMenuItems();
        log.info("Fetched {} available menu items", availableItems.size());
        return new ResponseEntity<>(availableItems, HttpStatus.OK);
    }
}
