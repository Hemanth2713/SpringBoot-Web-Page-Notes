package com.practice.SpringEcom;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.util.Date;
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Controller
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;
    private String productName;
    private String brand;
    private String description;
    private BigDecimal price;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="dd-MM-YYYY")
    private String category;
    private Date releaseDate;
    private boolean productAvailable;
    private int stockQuantity;


    public static class cutomer {
    }
}

