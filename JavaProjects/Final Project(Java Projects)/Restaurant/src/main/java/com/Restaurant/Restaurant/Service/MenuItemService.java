package com.Restaurant.Restaurant.Service;

import com.Restaurant.Restaurant.DTO.MenuItemDto;
import jakarta.validation.Valid;

import java.util.List;

public interface MenuItemService {
    void createMenu(@Valid MenuItemDto menuItemDto);

    List<MenuItemDto> getAllMenuItems();

    MenuItemDto getMenuItemById(Long id);

    void updateMenuItem(Long id, @Valid MenuItemDto menuItemDto);

    void toggleAvailabilityStatus(Long id);

    void deleteMenuItem(Long id);

    List<MenuItemDto> getAvailableMenuItems();
}
