package com.Delivery.FoodDelivery.Controller;

import com.Delivery.FoodDelivery.Model.Menu;
import com.Delivery.FoodDelivery.Model.OrderCart;
import com.Delivery.FoodDelivery.Model.Restaurant;
import com.Delivery.FoodDelivery.Model.User;
import com.Delivery.FoodDelivery.Service.MenuService;
import com.Delivery.FoodDelivery.Service.OrderCartService;
import com.Delivery.FoodDelivery.Service.RestaurantService;
import com.Delivery.FoodDelivery.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("orderCart")
@RestController
public class OrderCartController {
    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private OrderCartService orderCartService;

    @PostMapping
    public ResponseEntity<?> createOrderCart(@RequestBody OrderCart orderCart) {
        // Check if customer is valid
        User customer = userService.getUserById(orderCart.getUser().getUserId());
        if (customer == null || !"Customer".equalsIgnoreCase(customer.getRole())) {
            return new ResponseEntity<>("Invalid Customer Id", HttpStatus.NOT_FOUND);
        }

        // Check if restaurant exists
        Optional<Restaurant> restaurant = restaurantService.getRestaurantById(orderCart.getRestaurant().getRestaurantId());
        if (restaurant.isEmpty() || orderCart.getRestaurant().getRestaurantId() == null) {
            return new ResponseEntity<>("Restaurant Id is not Found", HttpStatus.NOT_FOUND);
        }

        // Check if menu exists
        Menu menu = menuService.getMenuById(orderCart.getMenu().getMenuId());
        if (menu == null || orderCart.getMenu().getMenuId() == null) {
            return new ResponseEntity<>("Menu Id is not found", HttpStatus.NOT_FOUND);
        }

        // Calculate the total price (quantity * menu price)
        double totalPrice = orderCart.getQuantity() * menu.getPrice();
        orderCart.setPrice(totalPrice); // Set the total price for the OrderCart

        // Create and save OrderCart
        OrderCart newOrderCart = orderCartService.createOrderCart(orderCart);

        return new ResponseEntity<>(newOrderCart, HttpStatus.CREATED);
    }


    @GetMapping()
    public ResponseEntity<List<OrderCart>> getOrderCartDetails(){
        List<OrderCart> orderCartList=orderCartService.getAllOrderCartDetails();
        return new ResponseEntity<>(orderCartList,HttpStatus.FOUND);
    }

    @GetMapping("Id/{orderCartId}")
    public ResponseEntity<?> getOrderCartById(@PathVariable("orderCartId") Long id) {
        OrderCart orderCart = orderCartService.getOrderCartById(id);
        if (orderCart == null) {
            return new ResponseEntity<>("OrderCart with ID " + id + " is not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(orderCart, HttpStatus.FOUND);
    }


    @PutMapping("Id/{orderCartId}")
    public ResponseEntity<OrderCart> updateOrderCartById(@PathVariable("orderCartId") Long id,@RequestBody OrderCart orderCart){
        OrderCart orderCartDetails=orderCartService.updateOrderCartById(id,orderCart);
        return new ResponseEntity<>(orderCartDetails,HttpStatus.CREATED);
    }
    @DeleteMapping("Id/{deleteItem}")
    public ResponseEntity<String> deleteItem(@PathVariable("deleteItem") Long menuId ){
        try{
            if(orderCartService.deleteById(menuId)){
                return new ResponseEntity<>("Successfully Deleted",HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>("OrderCart Id Not Found",HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}