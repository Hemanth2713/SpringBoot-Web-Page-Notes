package com.Restaurant.Restaurant.Service;

import com.Restaurant.Restaurant.DTO.MenuDto;
import jakarta.validation.Valid;

import java.util.List;

public interface MenuService {
    void createMenu(@Valid MenuDto menuDto);

    List<MenuDto> getAllMenus();

    MenuDto getMenuById(Long id);

    void updateMenu(Long id, @Valid MenuDto menuDto);

    void deleteMenu(Long id);

    List<MenuDto> getActiveMenus();

    void toggleMenuStatus(Long id);
}
