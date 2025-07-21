package com.practice.Task1.Controller;


import com.practice.Task1.Model.Customer;
import com.practice.Task1.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/customer")
@RestController
public class CustomerController {

    @Autowired
    private CustomerService service;

    @PostMapping("register")
    public Customer customerRegistration(@RequestBody Customer customer){
        return service.saveCustomer(customer);
    }

    @GetMapping("customers")
    public List<Customer> customerList(){
        return service.getAllCustomers();
    }
    @PutMapping("/update-password/{username}")
    public ResponseEntity<?> updatePassword(@PathVariable("username") String username, @RequestBody String newPassword) {
        // Fetch the customer by username
        Customer customer = service.getCustomerByUsername(username);
        if (customer != null) {
            // Update the password
            customer.setPassword(newPassword);
            // Save the updated customer back to the database
            service.saveCustomer(customer);
            return ResponseEntity.ok(customer); // Return updated customer
        } else {
            // Return an error response if customer is not found
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Customer with username '" + username + "' not found.");
        }
    }

    @DeleteMapping("/delete/{username}")
    public String deleteCustomerFromList(@PathVariable String username){
        service.deleteCustomer(username);
        return username+" Deleted from the List";
    }

}
