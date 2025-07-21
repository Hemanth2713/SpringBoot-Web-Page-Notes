package com.practice.Task1.Service;


import com.practice.Task1.Model.Customer;
import com.practice.Task1.Repo.CustomerRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CustomerService {
    @Autowired
    private CustomerRepo repo;

    public Customer saveCustomer(Customer customer) {
        return repo.save(customer);
    }

    public List<Customer> getAllCustomers() {
        return repo.findAll();
    }

    public Customer getCustomerByUsername(String username) {
        return repo.findByUsername(username);
    }


    public void deleteCustomer(String username) {
        repo.deleteByUsername(username);  // Call the repository method to delete by username
    }
}
