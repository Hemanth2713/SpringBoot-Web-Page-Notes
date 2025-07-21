package com.Restaurant.Restaurant.Service;

import com.Restaurant.Restaurant.DTO.AddressDto;
import jakarta.validation.Valid;

import java.util.List;

public interface AddressService {
    String createAddress(@Valid AddressDto addressDto);

    String updateAddress(Long addressId, @Valid AddressDto addressDto);

    AddressDto getAddress(Long addressId);

    List<AddressDto> getAllAddresses();

    String deleteAddress(Long addressId);
}
