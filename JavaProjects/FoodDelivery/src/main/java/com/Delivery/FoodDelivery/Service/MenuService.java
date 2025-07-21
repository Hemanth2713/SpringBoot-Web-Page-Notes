package com.Delivery.FoodDelivery.Service;

import com.Delivery.FoodDelivery.Model.Menu;
import com.Delivery.FoodDelivery.Model.Restaurant;
import com.Delivery.FoodDelivery.Repository.MenuRepository;
import com.Delivery.FoodDelivery.Repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class MenuService {
    @Autowired
    MenuRepository menuRepository;

    @Autowired
    RestaurantRepository restaurantRepository;
    public Menu addMenu(Menu menu) {
        // Validate if the restaurant exists in the database
        Restaurant restaurant = restaurantRepository.findById(menu.getRestaurant().getRestaurantId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant ID is not found"));

        // Check if the restaurant ID in the menu input matches the found restaurant
        if (!menu.getRestaurant().getRestaurantId().equals(restaurant.getRestaurantId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid restaurant ID. The restaurant ID in the input does not match.");
        }

        // Save the menu if the restaurant ID is valid
        return menuRepository.save(menu);
    }


    public List<Menu> getAllMenuDetails() {
        return menuRepository.findAll();
    }

    public Menu getMenuById(Long menuId) {
        return menuRepository.findById(menuId).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Menu not found with ID : "+menuId));
    }

    public List<Menu> getMenuByItemName(String itemName) {
        return  menuRepository.findByItemNameContainingIgnoreCase(itemName);
    }

    public List<Menu> getItemsByAvailability(boolean itemAvailability) {
        List<Menu> menuList=menuRepository.findByAvailability(itemAvailability);
        if (menuList.isEmpty()){
            System.out.println("Items are not Available");
        }
        return menuList;
    }

    public Menu updateMenu(Long menuId, Menu menuDetails) {
        Menu existingMenu=menuRepository.findById(menuId).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found with ID : "+menuId));

        existingMenu.setMenuId(existingMenu.getMenuId());
        existingMenu.setItemName(menuDetails.getItemName());
      //  existingMenu.setRestaurant(menuDetails.getRestaurant());
        existingMenu.setPrice(menuDetails.getPrice());
        existingMenu.setDescription(menuDetails.getDescription());
        existingMenu.setAvailability(menuDetails.isAvailability());
        return menuRepository.save(existingMenu);
    }

    public void deleteById(Long menuId) {
        // Check if the menu exists, throw exception if not
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Menu ID not found: " + menuId));

        // Delete the menu item
        menuRepository.delete(menu);
    }

}