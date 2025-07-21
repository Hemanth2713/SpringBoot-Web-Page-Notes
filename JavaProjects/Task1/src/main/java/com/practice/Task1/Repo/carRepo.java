package com.practice.Task1.Repo;

import com.practice.Task1.Model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Repository
public interface carRepo extends JpaRepository<Car,Integer> {

    public List<Car> findByCarName(String name);
}
