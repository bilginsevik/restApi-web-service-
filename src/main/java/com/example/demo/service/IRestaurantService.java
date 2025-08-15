package com.example.demo.service;

import com.example.demo.dto.RestaurantRequest;
import com.example.demo.dto.RestaurantResponse;

import java.util.List;
import java.util.Optional;

public interface IRestaurantService {

    List<RestaurantResponse> getAllRestaurants();

    RestaurantResponse addRestaurant(RestaurantRequest request);

    Optional<RestaurantResponse> getRestaurantById(Long id);

    RestaurantResponse updateRestaurant(Long id, RestaurantRequest updatedRequest);

    void deleteRestaurant(Long id);
}
