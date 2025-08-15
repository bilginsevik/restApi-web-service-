package com.example.demo.service;

import com.example.demo.model.Menu;

import java.util.List;

public interface IMenuService {

    Menu createMenu(Menu menu);

    List<Menu> getAllMenus();

    Menu getMenuById(Long id);

    Menu updateMenu(Long id, Menu menuDetails);

    void deleteMenu(Long id);
}
