package com.Restaurant.Restaurant.Repository;

import com.Restaurant.Restaurant.Model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address,Long> {
    Optional<Address> findByAddressIdAndUser_UserId(Long addressId, Long userId);
}
