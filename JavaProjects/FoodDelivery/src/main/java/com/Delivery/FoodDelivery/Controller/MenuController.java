package com.Delivery.FoodDelivery.Controller;

import com.Delivery.FoodDelivery.Model.Menu;
import com.Delivery.FoodDelivery.Model.Restaurant;
import com.Delivery.FoodDelivery.Service.MenuService;
import com.Delivery.FoodDelivery.Service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RequestMapping("/menu")
@RestController
public class MenuController {

    @Autowired
    MenuService menuService;

    @Autowired
    RestaurantService restaurantService;
    @PostMapping
    public ResponseEntity<?> addMenu(@RequestBody Menu menu) {
        // Validate if restaurant object or restaurantId is null
        if (menu.getRestaurant() == null || menu.getRestaurant().getRestaurantId() == null) {
            return new ResponseEntity<>("Restaurant ID cannot be null.", HttpStatus.BAD_REQUEST);
        }

        // Validate if the restaurant exists with the given restaurantId
        Optional<Restaurant> restaurantOptional = restaurantService.getRestaurantById(menu.getRestaurant().getRestaurantId());
        if (restaurantOptional.isEmpty()) {
            return new ResponseEntity<>("Invalid restaurant ID. Restaurant must exist.", HttpStatus.NOT_FOUND);
        }

        // Set the validated restaurant in the menu object
        menu.setRestaurant(restaurantOptional.get());

        // Save the menu object
        Menu savedMenu = menuService.addMenu(menu);

        // Return the saved menu object in the response
        return new ResponseEntity<>(savedMenu, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<Menu>> getAllMenuDetails(){
        List<Menu> menuList=menuService.getAllMenuDetails();
        return new ResponseEntity<>(menuList,HttpStatus.FOUND);
    }

    @GetMapping("Id/{itemId}")
    public ResponseEntity<Menu> getMenuById(@PathVariable("itemId") Long menuId){
       return new ResponseEntity<>(menuService.getMenuById(menuId),HttpStatus.FOUND);
    }

    @GetMapping("name/{itemName}")
    public ResponseEntity<List<Menu>> getMenuByName(@PathVariable String itemName){
        List<Menu> menuList=menuService.getMenuByItemName(itemName);
        if(menuList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(menuList,HttpStatus.FOUND);
        }
    }

    //I am getting error while working on this method
    @GetMapping("availability/itemAvailability")
    public ResponseEntity<List<Menu>> getItemsByAvailability(@PathVariable boolean itemAvailability){
       List<Menu> menuList=menuService.getItemsByAvailability(itemAvailability);
        if(menuList.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No items are available.");
            }
        else {
            return new ResponseEntity<>(menuList,HttpStatus.FOUND);
        }
    }

    @PutMapping("Id/{menuId}")
    public ResponseEntity<Menu> updateMenu(@PathVariable Long menuId,@RequestBody Menu menuDetails){
        Menu updatedMenu=menuService.updateMenu(menuId,menuDetails);
        return new ResponseEntity<>(updatedMenu,HttpStatus.OK);
    }

    @DeleteMapping("Id/{menuId}")
    public ResponseEntity<String> deleteItem(@PathVariable Long menuId) {
        try {
            menuService.deleteById(menuId);
            return new ResponseEntity<>("Menu item deleted successfully.", HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        } catch (Exception e) {
            return new ResponseEntity<>("An unexpected error occurred.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
