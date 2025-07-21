package com.practice.SecurityUsingBycrypt.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Component
public class studentSecurity {
    @Id
    private  Long id;
    private  String name;
    private String tech;
}
