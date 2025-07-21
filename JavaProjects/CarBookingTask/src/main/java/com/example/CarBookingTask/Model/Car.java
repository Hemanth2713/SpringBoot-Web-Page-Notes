package com.example.CarBookingTask.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Data

@Component
@Entity
public class Car {
    @Id
    @SequenceGenerator(
            name = "car_seq",
            sequenceName = "car_seq",
            allocationSize = 1,
            initialValue = 10000 // Start at 5-digit number
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "car_seq")
    private Long carId;

    private String company;
    private String model;
    private String numberPlate;
    private Integer seatingCapacity;
    private Integer carRange; // In kilometers
    private Integer fuelTankCapacity; // In liters
    private Double pricePerDay; // Price in currency per day
    private String status; // e.g., AVAILABLE, BOOKED, UNDER_MAINTENANCE

    @OneToMany(mappedBy = "car")
    private List<Booking> bookings; // Optional: To retrieve all bookings for this car

    // Getters and setters...

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getNumberPlate() {
        return numberPlate;
    }

    public void setNumberPlate(String numberPlate) {
        this.numberPlate = numberPlate;
    }

    public Integer getSeatingCapacity() {
        return seatingCapacity;
    }

    public void setSeatingCapacity(Integer seatingCapacity) {
        this.seatingCapacity = seatingCapacity;
    }

    public Integer getCarRange() {
        return carRange;
    }

    public void setCarRange(Integer carRange) {
        this.carRange = carRange;
    }

    public Integer getFuelTankCapacity() {
        return fuelTankCapacity;
    }

    public void setFuelTankCapacity(Integer fuelTankCapacity) {
        this.fuelTankCapacity = fuelTankCapacity;
    }

    public Double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(Double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
