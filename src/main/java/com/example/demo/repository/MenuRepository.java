package com.example.demo.repository;

import com.example.demo.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MenuRepository extends JpaRepository<Menu, Long> {

    @Query("SELECT m FROM Menu m LEFT JOIN FETCH m.menuItems WHERE m.id = :id")
    Optional<Menu> findByIdWithMenuItems(@Param("id") Long id);

    @Query("SELECT DISTINCT m FROM Menu m LEFT JOIN FETCH m.menuItems")
    List<Menu> findAllWithMenuItems();
}
