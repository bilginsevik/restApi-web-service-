package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantRequest {
    private String restaurantName;
    private String restaurantLocation;
    private String phone;
    private String email;
    private String address;
}
