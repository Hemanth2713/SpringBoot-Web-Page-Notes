package com.Restaurant.Restaurant.ServiceImplementation;

import com.Restaurant.Restaurant.DTO.AddressDto;
import com.Restaurant.Restaurant.Model.Address;
import com.Restaurant.Restaurant.Model.User;
import com.Restaurant.Restaurant.Repository.AddressRepository;
import com.Restaurant.Restaurant.Service.AddressService;
import com.Restaurant.Restaurant.utils.CustomUtils;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AddressServiceImplementation implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public String createAddress(AddressDto addressDto) {
        log.info("Creating address for DTO: {}", addressDto);

        Address address = this.modelMapper.map(addressDto, Address.class);
        User user = CustomUtils.getCurrentLoggedInUser();
        address.setUser(user);

        Address savedAddress = addressRepository.save(address);
        log.info("Address created successfully with ID: {}", savedAddress.getAddressId());

        return "Address Created Successfully";
    }

    @Override
    public String updateAddress(Long addressId, AddressDto addressDto) {
        Long userId = CustomUtils.getCurrentLoggedInUser().getUserId();
        log.info("Updating address ID {} for user ID {}", addressId, userId);

        Address existingAddress = addressRepository.findByAddressIdAndUser_UserId(addressId, userId)
                .orElseThrow(() -> {
                    log.warn("Address with ID {} not found for user ID {}", addressId, userId);
                    return new IllegalArgumentException("Address not found"); // Change Exception in Future
                });

        existingAddress.setHouseNumber(addressDto.getHouseNumber());
        existingAddress.setStreet(addressDto.getStreet());
        existingAddress.setArea(addressDto.getArea());
        existingAddress.setLandmark(addressDto.getLandmark());
        existingAddress.setCity(addressDto.getCity());
        existingAddress.setState(addressDto.getState());
        existingAddress.setPincode(addressDto.getPincode());

        log.info("Address updated successfully for ID {}", addressId);
        return "Address Updated SuccessFully";
    }

    @Override
    public AddressDto getAddress(Long addressId) {
        Long userId = CustomUtils.getCurrentLoggedInUser().getUserId();
        log.info("Fetching address with ID {} for user ID {}", addressId, userId);

        Address address = addressRepository.findByAddressIdAndUser_UserId(addressId, userId).orElseThrow(() -> {
            log.warn("Address with ID {} not found for user ID {}", addressId, userId);
            return new IllegalArgumentException("Address not found"); // Change Exception in Future
        });

        AddressDto addressDto = this.modelMapper.map(address, AddressDto.class);
        log.info("Fetched address: {}", addressDto);
        return addressDto;
    }

    @Override
    public List<AddressDto> getAllAddresses() {
        log.info("Fetching all addresses");

        List<AddressDto> addressDtoList = addressRepository.findAll().stream()
                .map(address -> modelMapper.map(address, AddressDto.class))
                .collect(Collectors.toList());

        log.info("Total addresses fetched: {}", addressDtoList.size());
        return addressDtoList;
    }

    @Override
    public String deleteAddress(Long addressId) {
        Long userId = CustomUtils.getCurrentLoggedInUser().getUserId();
        log.info("Attempting to delete address with ID {} for user ID {}", addressId, userId);

        Address address = addressRepository.findByAddressIdAndUser_UserId(addressId, userId).orElseThrow(() -> {
            log.warn("Address with ID {} not found for user ID {}", addressId, userId);
            return new IllegalArgumentException("Address not found"); // Change Exception in Future
        });

        addressRepository.delete(address);
        log.info("Address with ID {} deleted successfully", addressId);
        return "Address deleted Successfully";
    }
}
