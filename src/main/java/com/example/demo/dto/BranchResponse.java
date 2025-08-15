package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BranchResponse {
    private Long id;
    private String name;
    private String address;
    private String status;         // "Aktif" veya "Pasif" yazacak
    private String restaurantName;

    public BranchResponse() {}

    public BranchResponse(Long id, String name, String address, String status, String restaurantName) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.status = status;
        this.restaurantName = restaurantName;
    }
}
