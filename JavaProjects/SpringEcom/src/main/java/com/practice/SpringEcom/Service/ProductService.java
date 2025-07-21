package com.practice.SpringEcom.Service;

import com.practice.SpringEcom.Product;
import com.practice.SpringEcom.Repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepo repo;
    public List<Product> getAllProducts() {
        return repo.findAll();
    }

    public Product getByProductId(int id) {
        return  repo.findById(id).orElse(new Product());
    }
}
