package com.practice.Task1.Repo;

import com.practice.Task1.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepo extends JpaRepository<Customer,Integer> {
    Customer findByUsername(String username);

   // @Query(value = "delete * from customer where :username",nativeQuery = true)
    void deleteByUsername(String username);
}
