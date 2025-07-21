package com.example.CarBookingTask.Repository;

import com.example.CarBookingTask.Model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car,Long> {
    List<Car> findByCompany(String company);
    List<Car> findByModel(String model);
    List<Car> findBySeatingCapacity(Integer seatingCapacity);
    List<Car> findByCompanyAndModel(String company, String model);

    Car findByCarIdAndStatus(Long carId, String available);
}
