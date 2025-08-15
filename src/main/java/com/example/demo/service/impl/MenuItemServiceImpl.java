package com.example.demo.service.impl;

import com.example.demo.model.Menu;
import com.example.demo.model.MenuItem;
import com.example.demo.repository.MenuItemRepository;
import com.example.demo.repository.MenuRepository;
import com.example.demo.service.IMenuItemService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class MenuItemServiceImpl implements IMenuItemService {

    private final MenuItemRepository menuItemRepository;
    private final MenuRepository menuRepository;

    public MenuItemServiceImpl(MenuItemRepository menuItemRepository, MenuRepository menuRepository) {
        this.menuItemRepository = menuItemRepository;
        this.menuRepository = menuRepository;
    }

    @Override
    public MenuItem addMenuItemToMenu(Long menuId, String itemName, String description, String category, double price, boolean isAvailable) {
        Menu menu = menuRepository.findById(menuId).orElse(null);
        if (menu != null) {
            MenuItem item = new MenuItem();
            item.setName(itemName);
            item.setDescription(description);
            item.setCategory(category);
            item.setPrice(BigDecimal.valueOf(price));
            item.setAvailable(isAvailable);
            item.setMenu(menu);
            return menuItemRepository.save(item);
        }
        return null;
    }

    @Override
    public List<MenuItem> getAllMenuItems() {
        return menuItemRepository.findAll();
    }
}
