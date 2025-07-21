package com.Restaurant.Restaurant.Controller;

import com.Restaurant.Restaurant.DTO.MenuDto;
import com.Restaurant.Restaurant.Service.MenuService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @PostMapping
    public ResponseEntity<String> createMenu(@Valid @RequestBody MenuDto menuDto) {
        log.info("Creating new menu: {}", menuDto);
        menuService.createMenu(menuDto);
        log.info("Menu created successfully");
        return new ResponseEntity<>("Menu added successfully", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<MenuDto>> getAllMenus() {
        log.info("Fetching all menus");
        List<MenuDto> menus = menuService.getAllMenus();
        log.info("Fetched {} menus", menus.size());
        return new ResponseEntity<>(menus, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuDto> getMenuById(@PathVariable Long id) {
        log.info("Fetching menu by ID: {}", id);
        MenuDto menuDto = menuService.getMenuById(id);
        log.info("Menu found: {}", menuDto);
        return new ResponseEntity<>(menuDto, HttpStatus.OK);
    }

    @PutMapping("/{id}/toggle-status")
    public ResponseEntity<String> toggleMenuStatus(@PathVariable Long id) {
        log.info("Toggling status of menu ID {}", id);
        menuService.toggleMenuStatus(id);
        return new ResponseEntity<>("Menu status toggled successfully", HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<String> updateMenu(@PathVariable Long id, @Valid @RequestBody MenuDto menuDto) {
        log.info("Updating menu ID {}: {}", id, menuDto);
        menuService.updateMenu(id, menuDto);
        log.info("Menu updated successfully");
        return new ResponseEntity<>("Menu updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMenu(@PathVariable Long id) {
        log.info("Deleting menu ID: {}", id);
        menuService.deleteMenu(id);
        log.info("Menu deleted successfully");
        return new ResponseEntity<>("Menu deleted successfully", HttpStatus.OK);
    }

    @GetMapping("/active")
    public ResponseEntity<List<MenuDto>> getActiveMenus() {
        log.info("Fetching active menus");
        List<MenuDto> activeMenus = menuService.getActiveMenus();
        log.info("Fetched {} active menus", activeMenus.size());
        return new ResponseEntity<>(activeMenus, HttpStatus.OK);
    }
}
