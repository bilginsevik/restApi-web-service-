package com.example.demo.controller;

import com.example.demo.dto.MenuItemRequest;
import com.example.demo.model.MenuItem;
import com.example.demo.service.IMenuItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menuitems")
public class MenuItemController {

    private final IMenuItemService menuItemService;

    public MenuItemController(IMenuItemService menuItemService) {
        this.menuItemService = menuItemService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<MenuItem>> getAllMenuItems() {
        List<MenuItem> items = menuItemService.getAllMenuItems();
        return ResponseEntity.ok(items);
    }

    @PostMapping("/add")
    public ResponseEntity<MenuItem> addMenuItem(@RequestBody MenuItemRequest menuItemRequest) {
        MenuItem createdItem = menuItemService.addMenuItemToMenu(
                menuItemRequest.getMenuId(),
                menuItemRequest.getName(),
                menuItemRequest.getDescription(),
                menuItemRequest.getCategory(),
                menuItemRequest.getPrice(),
                menuItemRequest.isAvailable()
        );
        return ResponseEntity.ok(createdItem);
    }
}
