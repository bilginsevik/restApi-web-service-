package com.example.demo.service.impl;

import com.example.demo.model.Menu;
import com.example.demo.repository.MenuRepository;
import com.example.demo.service.IMenuService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements IMenuService {

    private final MenuRepository menuRepository;

    public MenuServiceImpl(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @Override
    public Menu createMenu(Menu menu) {
        return menuRepository.save(menu);
    }

    @Override
    public List<Menu> getAllMenus() {
        return menuRepository.findAllWithMenuItems();
    }

    @Override
    public Menu getMenuById(Long id) {
        return menuRepository.findByIdWithMenuItems(id)
                .orElseThrow(() -> new RuntimeException("Menu bulunamadı"));
    }

    @Override
    public Menu updateMenu(Long id, Menu menuDetails) {
        Menu menu = getMenuById(id);
        menu.setName(menuDetails.getName());
        menu.setDescription(menuDetails.getDescription());
        menu.setBranch(menuDetails.getBranch());
        menu.setRestaurant(menuDetails.getRestaurant());

        return menuRepository.save(menu);
    }

    @Override
    public void deleteMenu(Long id) {
        Menu menu = getMenuById(id);
        menuRepository.delete(menu);
    }
}
