package com.Restaurant.Restaurant.Controller;

import com.Restaurant.Restaurant.DTO.AddressDto;
import com.Restaurant.Restaurant.Service.AddressService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping
    public ResponseEntity<String> createAddress(@Valid @RequestBody AddressDto addressDto) {
        log.info("Received request to create address: {}", addressDto);

        String createdAddress = addressService.createAddress(addressDto);
        log.info("Address created successfully: {}", createdAddress);

        return new ResponseEntity<>(createdAddress, HttpStatus.CREATED);
    }

    @PutMapping("/{addressId}")
    public ResponseEntity<String> updateAddress(@PathVariable Long addressId,
                                                @Valid @RequestBody AddressDto addressDto) {
        log.info("Received request to update address with ID {}: {}", addressId, addressDto);

        String updatedAddress = addressService.updateAddress(addressId, addressDto);
        log.info("Address updated successfully for ID {}: {}", addressId, updatedAddress);

        return new ResponseEntity<>(updatedAddress, HttpStatus.OK);
    }

    @GetMapping("/{addressId}")
    public ResponseEntity<AddressDto> getAddress(@PathVariable Long addressId) {
        log.info("Received request to fetch address with ID {}", addressId);

        AddressDto address = addressService.getAddress(addressId);
        log.info("Fetched address for ID {}: {}", addressId, address);

        return new ResponseEntity<>(address, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<AddressDto>> getAllAddresses() {
        log.info("Received request to fetch all addresses");

        List<AddressDto> allAddresses = addressService.getAllAddresses();
        log.info("Fetched {} addresses", allAddresses.size());

        return new ResponseEntity<>(allAddresses, HttpStatus.OK);
    }

    @DeleteMapping("/{addressId}")
    public ResponseEntity<String> deleteAddress(@PathVariable Long addressId) {
        log.info("Received request to delete address with ID {}", addressId);

        String deletedAddress = addressService.deleteAddress(addressId);
        log.info("Address deleted successfully for ID {}: {}", addressId, deletedAddress);

        return new ResponseEntity<>(deletedAddress, HttpStatus.OK);
    }
}
