package com.Restaurant.Restaurant.ServiceImplementation;

import com.Restaurant.Restaurant.DTO.MenuItemDto;
import com.Restaurant.Restaurant.Model.AvailabilityStatus;
import com.Restaurant.Restaurant.Model.Menu;
import com.Restaurant.Restaurant.Model.MenuItem;
import com.Restaurant.Restaurant.Repository.MenuItemRepository;
import com.Restaurant.Restaurant.Repository.MenuRepository;
import com.Restaurant.Restaurant.Service.MenuItemService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class MenuItemServiceImplementation implements MenuItemService {


    @Autowired
    private MenuItemRepository menuItemRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MenuRepository menuRepository;

//    @Override
//    @Transactional
//    public void createMenu(MenuItemDto menuItemDto) {
//        logger.info("Attempting to create menu item: {}", menuItemDto.getItemName());
//
//        MenuItem existingMenuItem = menuItemRepository.findByItemName(menuItemDto.getItemName());
//
//        if (existingMenuItem == null) {
//            logger.info("Menu item '{}' not found in the database. Proceeding to create.", menuItemDto.getItemName());
//
//            Optional<Menu> menuOptional = menuRepository.findById(menuItemDto.getMenuId());
//
//            if (menuOptional.isPresent()) {
//                logger.info("Menu with ID {} found. Mapping and saving the menu item.", menuItemDto.getMenuId());
//
//                MenuItem menuItem = modelMapper.map(menuItemDto, MenuItem.class);
//                menuItem.setMenu(menuOptional.get());
//                menuItem.setAvailability(AvailabilityStatus.AVAILABLE);
//
//                menuItemRepository.save(menuItem);
//
//                logger.info("Menu item '{}' created successfully under menu ID {}.",
//                        menuItem.getItemName(), menuItemDto.getMenuId());
//            } else {
//                logger.error("Menu ID {} not found. Cannot create menu item.", menuItemDto.getMenuId());
//                throw new RuntimeException("Menu ID " + menuItemDto.getMenuId() + " not found in the Menu database.");
//            }
//        } else {
//            logger.warn("Menu item with name '{}' already exists. Creation aborted.", menuItemDto.getItemName());
//            throw new RuntimeException("Menu item with name '" + menuItemDto.getItemName() + "' already exists.");
//        }
//    }

    @Override
    @Transactional
    public void createMenu(MenuItemDto menuItemDto) {
        log.info("Attempting to create menu item: {}", menuItemDto.getItemName());

        // ✅ Check for duplicate item name (optional)
        MenuItem existingItem = menuItemRepository.findByItemName(menuItemDto.getItemName());
        if (existingItem != null) {
            log.warn("Menu item '{}' already exists. Aborting creation.", menuItemDto.getItemName());
            throw new RuntimeException("Menu item with name '" + menuItemDto.getItemName() + "' already exists.");
        }

        // ✅ Verify menu exists
        Menu menu = menuRepository.findById(menuItemDto.getMenuId())
                .orElseThrow(() -> {
                    log.error("Menu ID {} not found. Cannot create menu item.", menuItemDto.getMenuId());
                    return new RuntimeException("Menu with ID '" + menuItemDto.getMenuId() + "' not found.");
                });

        // ✅ Map and prepare new MenuItem
        MenuItem menuItem = modelMapper.map(menuItemDto, MenuItem.class);

        // 🚫 Prevent updating an existing item accidentally
        menuItem.setItemId(null); // This is KEY for CREATE

        // ✅ Set menu relationship
        menuItem.setMenu(menu);

        // ✅ Set default availability status
        menuItem.setAvailability(AvailabilityStatus.AVAILABLE);
        menuItem.setCreatedAt(LocalDateTime.now());

        // ✅ Save
        menuItemRepository.save(menuItem);

        log.info("Menu item '{}' created successfully under menu '{}'.",
                menuItem.getItemName(), menu.getMenuName());
    }

    @Override
    public List<MenuItemDto> getAllMenuItems() {
        log.info("Fetching all menu items from the database.");
        List<MenuItemDto> menuItemDtos = menuItemRepository.findAll()
                .stream().map(menuItem -> modelMapper.map(menuItem, MenuItemDto.class)).toList();
        log.info("Fetched {} menu items.", menuItemDtos.size());
        return menuItemDtos;
    }

    @Override
    public MenuItemDto getMenuItemById(Long id) {
        log.info("Fetching menu item by ID: {}", id);
        MenuItem menuItem = menuItemRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Menu item with ID {} not found.", id);
                    return new RuntimeException("Menu item with ID " + id + " not found.");
                });
        log.info("Menu item with ID {} found: {}", id, menuItem.getItemName());
        return modelMapper.map(menuItem, MenuItemDto.class);
    }

    @Override
    @Transactional
    public void updateMenuItem(Long id, MenuItemDto menuItemDto) {
        log.info("Attempting to update menu item ID {}.", id);
        MenuItem existingMenuItem = menuItemRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Menu item with ID {} not found.", id);
                    return new RuntimeException("Menu item with ID " + id + " not found.");
                });

        Menu menu = menuRepository.findById(menuItemDto.getMenuId())
                .orElseThrow(() -> {
                    log.error("Menu ID {} not found.", menuItemDto.getMenuId());
                    return new RuntimeException("Menu ID " + menuItemDto.getMenuId() + " not found.");
                });

        existingMenuItem.setItemName(menuItemDto.getItemName());
        existingMenuItem.setDescription(menuItemDto.getDescription());
        existingMenuItem.setUpdatedAt(LocalDateTime.now());
        existingMenuItem.setPrice(menuItemDto.getPrice());
        existingMenuItem.setMenu(menu);

        menuItemRepository.save(existingMenuItem);
        log.info("Menu item ID {} updated successfully.", id);
    }

    @Override
    @Transactional
    public void toggleAvailabilityStatus(Long id) {
        log.info("Toggling availability status for menu item ID {}.", id);
        MenuItem menuItem = menuItemRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Menu item with ID {} not found.", id);
                    return new RuntimeException("Menu item with ID " + id + " not found.");
                });

        AvailabilityStatus newStatus = (menuItem.getAvailability() == AvailabilityStatus.AVAILABLE)
                ? AvailabilityStatus.NOT_AVAILABLE
                : AvailabilityStatus.AVAILABLE;

        menuItem.setAvailability(newStatus);
        menuItemRepository.save(menuItem);
        log.info("Availability for menu item ID {} changed to {}.", id, newStatus);
    }

    @Override
    @Transactional
    public void deleteMenuItem(Long id) {
        log.info("Attempting to delete menu item ID {}.", id);
        MenuItem menuItem = menuItemRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Menu item with ID {} not found.", id);
                    return new RuntimeException("Menu item with ID " + id + " not found.");
                });

        menuItemRepository.delete(menuItem);
        log.info("Menu item ID {} deleted successfully.", id);
    }

    @Override
    public List<MenuItemDto> getAvailableMenuItems() {
        log.info("Fetching all AVAILABLE menu items.");
        List<MenuItem> availableItems = menuItemRepository.findByAvailability(AvailabilityStatus.AVAILABLE);
        List<MenuItemDto> dtoList = availableItems.stream()
                .map(item -> modelMapper.map(item, MenuItemDto.class))
                .toList();
        log.info("Fetched {} available menu items.", dtoList.size());
        return dtoList;
    }
}
