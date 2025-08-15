package com.example.demo.service;

import com.example.demo.model.MenuItem;

import java.util.List;

public interface IMenuItemService {

    MenuItem addMenuItemToMenu(Long menuId, String itemName, String description, String category, double price, boolean isAvailable);

    List<MenuItem> getAllMenuItems();
}
