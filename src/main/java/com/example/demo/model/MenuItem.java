package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "menu_item")
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String category;

    private BigDecimal price;

    @Column(name = "is_available")
    private boolean isAvailable;

    @ManyToOne
    @JoinColumn(name = "menu_id")
    @JsonBackReference("menu-menuItems")
    private Menu menu;
}
