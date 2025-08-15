package com.example.demo.controller;

import com.example.demo.model.Menu;
import com.example.demo.service.IMenuService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menus")
public class MenuController {

    private final IMenuService menuService;

    public MenuController(IMenuService menuService) {
        this.menuService = menuService;
    }

    @PostMapping
    public ResponseEntity<Menu> createMenu(@RequestBody Menu menu) {
        Menu created = menuService.createMenu(menu);
        return ResponseEntity.ok(created);
    }

    @GetMapping
    public ResponseEntity<List<Menu>> getAllMenus() {
        List<Menu> menus = menuService.getAllMenus();
        return ResponseEntity.ok(menus);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Menu> getMenuById(@PathVariable Long id) {
        Menu menu = menuService.getMenuById(id);
        return ResponseEntity.ok(menu);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Menu> updateMenu(@PathVariable Long id, @RequestBody Menu menu) {
        Menu updated = menuService.updateMenu(id, menu);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMenu(@PathVariable Long id) {
        menuService.deleteMenu(id);
        return ResponseEntity.noContent().build();
    }
}
