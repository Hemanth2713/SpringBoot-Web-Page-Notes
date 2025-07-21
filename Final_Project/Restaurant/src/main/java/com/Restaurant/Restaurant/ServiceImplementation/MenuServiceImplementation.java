package com.Restaurant.Restaurant.ServiceImplementation;

import com.Restaurant.Restaurant.DTO.MenuDto;
import com.Restaurant.Restaurant.Model.ActiveStatus;
import com.Restaurant.Restaurant.Model.Menu;
import com.Restaurant.Restaurant.Repository.MenuRepository;
import com.Restaurant.Restaurant.Service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@Slf4j
public class MenuServiceImplementation implements MenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional
    public void createMenu(MenuDto menuDto) {
        log.info("Attempting to create menu with name: {}", menuDto.getMenuName());

        try {
            Menu existingMenu = menuRepository.findByMenuName(menuDto.getMenuName());

            if (existingMenu == null) {
                Menu saveMenu = modelMapper.map(menuDto, Menu.class);
                saveMenu.setCreatedAt(LocalDateTime.now());
                saveMenu.setStatus(ActiveStatus.ACTIVE);
                menuRepository.save(saveMenu);

                log.info("Menu created successfully: {}", saveMenu);
            } else {
                log.warn("Menu with name '{}' already exists. Creation aborted.", menuDto.getMenuName());
                throw new IllegalArgumentException("Menu with name '" + menuDto.getMenuName() + "' already exists.");
            }

        } catch (IllegalArgumentException ex) {
            // Known error - duplicate menu
            log.error("Error creating menu: {}", ex.getMessage());
            throw ex; // Or wrap in a custom exception

        } catch (Exception ex) {
            // Unknown error - log and rethrow
            log.error("Unexpected error occurred while creating menu", ex);
            throw new RuntimeException("Something went wrong while creating the menu. Please try again later.");
        }
    }

    @Override
    public List<MenuDto> getAllMenus() {
        log.info("Fetching all menus from the database");
        List<MenuDto> menus = menuRepository.findAll()
                .stream()
                .map(menu -> modelMapper.map(menu, MenuDto.class))
                .toList();
        log.info("Fetched {} menus", menus.size());
        return menus;
    }

    @Override
    public MenuDto getMenuById(Long id) {
        log.info("Fetching menu by ID: {}", id);

        Menu menu = menuRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Menu with ID {} not found.", id);
                    return new RuntimeException("Menu not found with ID: " + id);
                });

        log.info("Menu found: {}", menu.getMenuName());
        return modelMapper.map(menu, MenuDto.class);
    }

    @Override
    @Transactional
    public void updateMenu(Long id, MenuDto menuDto) {
        log.info("Attempting to update menu ID {} with data: {}", id, menuDto);

        Menu existingMenu = menuRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Menu with ID {} not found. Cannot update.", id);
                    return new RuntimeException("Menu not found with ID: " + id);
                });

        existingMenu.setUpdatedAt(LocalDateTime.now());
        existingMenu.setMenuName(menuDto.getMenuName());
        existingMenu.setDescription(menuDto.getDescription());
        menuRepository.save(existingMenu);

        log.info("Menu updated successfully: {}", existingMenu);
    }

    @Override
    @Transactional
    public void toggleMenuStatus(Long id) {
        log.info("Toggling active status for menu ID {}", id);

        Menu menu = menuRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Menu with ID {} not found. Cannot toggle status.", id);
                    return new RuntimeException("Menu not found with ID: " + id);
                });

        ActiveStatus currentStatus = menu.getStatus();
        ActiveStatus newStatus = (currentStatus == ActiveStatus.ACTIVE)
                ? ActiveStatus.INACTIVE
                : ActiveStatus.ACTIVE;

        menu.setStatus(newStatus);
        menuRepository.save(menu);

        log.info("Menu ID {} status changed from {} to {}", id, currentStatus, newStatus);
    }

    @Override
    @Transactional
    public void deleteMenu(Long id) {
        log.info("Attempting to delete menu with ID: {}", id);

        if (!menuRepository.existsById(id)) {
            log.error("Menu with ID {} not found. Cannot delete.", id);
            throw new RuntimeException("Menu not found with ID: " + id);
        }

        menuRepository.deleteById(id);

        log.info("Menu deleted successfully with ID: {}", id);
    }

    @Override
    public List<MenuDto> getActiveMenus() {
        log.info("Fetching all ACTIVE menus from the database");

        List<Menu> activeMenus = menuRepository.findByStatus(ActiveStatus.ACTIVE);
        List<MenuDto> activeMenuDtos = activeMenus.stream()
                .map(menu -> modelMapper.map(menu, MenuDto.class))
                .toList();

        log.info("Fetched {} active menus", activeMenuDtos.size());
        return activeMenuDtos;
    }
}
