package com.example.CarBookingTask.Service;

import com.example.CarBookingTask.Model.Customer;
import com.example.CarBookingTask.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository repository;


    public Customer registerCustomer(Customer customer) {
        return repository.save(customer);
    }

    public List<Customer> getAllCustomers() {
        return repository.findAll();
    }

    public Customer getCustomerById(Long id) {
        return repository.findById(id).orElse(null);
    }
    public Customer getCustomerByLicenseNumber(String licenseNumber) {
        return repository.findByLicenseNumber(licenseNumber).orElse(null);
    }

    public Customer getCustomerByEmail(String email) {
        return repository.findByEmail(email).orElse(null);
    }

    public Customer updateCustomerDetails(Long id, String phoneNumber, String address) {
        Optional<Customer> optionalCustomer = repository.findById(id);
        if (optionalCustomer.isEmpty()) {
            return null;
        }
        Customer customer = optionalCustomer.get();
        customer.setPhoneNumber(phoneNumber);
        customer.setAddress(address);
        return repository.save(customer);
    }



}
