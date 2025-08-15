package com.example.demo.controller;

import com.example.demo.dto.RestaurantRequest;
import com.example.demo.dto.RestaurantResponse;
import com.example.demo.service.IRestaurantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/api/restaurants")
public class RestaurantController {

    private final IRestaurantService restaurantService;

    public RestaurantController(IRestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping
    public List<RestaurantResponse> getAllRestaurants() {
        return restaurantService.getAllRestaurants();
    }

    @PostMapping
    public RestaurantResponse addRestaurant(@RequestBody RestaurantRequest request) {
        return restaurantService.addRestaurant(request);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantResponse> getRestaurantById(@PathVariable Long id) {
        return restaurantService.getRestaurantById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public RestaurantResponse updateRestaurant(@PathVariable Long id, @RequestBody RestaurantRequest request) {
        return restaurantService.updateRestaurant(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable Long id) {
        restaurantService.deleteRestaurant(id);
        return ResponseEntity.noContent().build();
    }
}
