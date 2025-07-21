package com.practice.Task1.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@Entity
public class Car {
    @Id
    private  int carId;
    private  String carName;
    private String carModel;
    private  String carNumber;
    private  double carPrice;
    private int seatingCapacity;
    private  double fuelCapacity;
    private double carKilometersRange;
    private int kilometersDriven;
}
