package com.Restaurant.Restaurant.Repository;

import com.Restaurant.Restaurant.Model.ActiveStatus;
import com.Restaurant.Restaurant.Model.Menu;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu,Long> {
    Menu findByMenuName(String menuName);

    List<Menu> findByStatus(ActiveStatus activeStatus);
}
