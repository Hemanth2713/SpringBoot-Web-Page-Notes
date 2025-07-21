package com.practice.SpringEcom.Controller;

import com.practice.SpringEcom.Product;
import com.practice.SpringEcom.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    private ProductService service;
    @GetMapping("/products")
    public List<Product> getProducts(){
        return service.getAllProducts();
        }

    @GetMapping("/product/{id}")
    public Product productById(@PathVariable int id){
        return  service.getByProductId(id);
    }

}
