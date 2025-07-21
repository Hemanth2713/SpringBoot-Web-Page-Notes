package com.Restaurant.Restaurant.Repository;

import java.util.Optional;

import com.Restaurant.Restaurant.Model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

	@Query(value = "SELECT * FROM cart WHERE user_id = :userId", nativeQuery = true)
	Optional<Cart> findByUserId(Long userId);

}
