package com.example.CarBookingTask.Controller;
import com.example.CarBookingTask.Model.Customer;
import com.example.CarBookingTask.Service.CustomerService;
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

    @PostMapping("/customerRegister")
    public Customer registerCustomerDetails(@RequestBody Customer customer){
        return service.registerCustomer(customer);
    }

    @GetMapping("/customers")
    public List<Customer> getAllCustomerDetails(){
        return service.getAllCustomers();
    }

    @GetMapping("/customerId/{id}")
    public ResponseEntity<?> customerById(@PathVariable Long id) {
        Customer customer = service.getCustomerById(id);
        if (customer == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer with ID " + id + " does not exist");
        }
        return ResponseEntity.ok(customer);
    }

    @GetMapping("/license/{licenseNumber}")
    public ResponseEntity<?> getCustomerByLicenseNumber(@PathVariable String licenseNumber) {
        Customer customer = service.getCustomerByLicenseNumber(licenseNumber);
        if (customer == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer with license number " + licenseNumber + " does not exist");
        }
        return ResponseEntity.ok(customer);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> getCustomerByEmail(@PathVariable String email) {
        Customer customer = service.getCustomerByEmail(email);
        if (customer == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer with email " + email + " does not exist");
        }
        return ResponseEntity.ok(customer);
    }

    @PutMapping("/customer/update/{id}")
    public ResponseEntity<?> updateCustomerDetails(
            @PathVariable Long id,
            @RequestParam String phoneNumber,
            @RequestParam String address) {
        Customer updatedCustomer = service.updateCustomerDetails(id, phoneNumber, address);
        if (updatedCustomer == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer with ID " + id + " does not exist");
        }
        return ResponseEntity.ok(updatedCustomer);
    }



}
