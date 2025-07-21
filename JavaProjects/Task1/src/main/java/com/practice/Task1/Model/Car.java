package com.practice.Task1.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

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
    @OneToMany(mappedBy = "car")
    private List<Booking> bookings; // A car can have many bookings
}
