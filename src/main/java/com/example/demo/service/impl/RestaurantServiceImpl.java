package com.example.demo.service.impl;

import com.example.demo.dto.RestaurantRequest;
import com.example.demo.dto.RestaurantResponse;
import com.example.demo.model.Restaurant;
import com.example.demo.repository.RestaurantRepository;
import com.example.demo.service.IRestaurantService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class RestaurantServiceImpl implements IRestaurantService {

    private final RestaurantRepository restaurantRepository;

    public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public List<RestaurantResponse> getAllRestaurants() {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        return restaurants.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public RestaurantResponse addRestaurant(RestaurantRequest request) {
        Restaurant restaurant = new Restaurant();
        restaurant.setRestaurantName(request.getRestaurantName());
        restaurant.setRestaurantLocation(request.getRestaurantLocation());
        restaurant.setPhone(request.getPhone());
        restaurant.setEmail(request.getEmail());
        restaurant.setAddress(request.getAddress());

        Restaurant saved = restaurantRepository.save(restaurant);
        return convertToResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<RestaurantResponse> getRestaurantById(Long id) {
        return restaurantRepository.findById(id).map(this::convertToResponse);
    }

    @Override
    public RestaurantResponse updateRestaurant(Long id, RestaurantRequest updatedRequest) {
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(id);
        if (optionalRestaurant.isPresent()) {
            Restaurant restaurant = optionalRestaurant.get();
            restaurant.setRestaurantName(updatedRequest.getRestaurantName());
            restaurant.setRestaurantLocation(updatedRequest.getRestaurantLocation());
            restaurant.setPhone(updatedRequest.getPhone());
            restaurant.setEmail(updatedRequest.getEmail());
            restaurant.setAddress(updatedRequest.getAddress());
            Restaurant updated = restaurantRepository.save(restaurant);
            return convertToResponse(updated);
        } else {
            Restaurant restaurant = new Restaurant();
            restaurant.setId(id);
            restaurant.setRestaurantName(updatedRequest.getRestaurantName());
            restaurant.setRestaurantLocation(updatedRequest.getRestaurantLocation());
            restaurant.setPhone(updatedRequest.getPhone());
            restaurant.setEmail(updatedRequest.getEmail());
            restaurant.setAddress(updatedRequest.getAddress());
            Restaurant saved = restaurantRepository.save(restaurant);
            return convertToResponse(saved);
        }
    }

    @Override
    public void deleteRestaurant(Long id) {
        restaurantRepository.deleteById(id);
    }

    private RestaurantResponse convertToResponse(Restaurant restaurant) {
        return new RestaurantResponse(
                restaurant.getId(),
                restaurant.getRestaurantName(),
                restaurant.getRestaurantLocation(),
                restaurant.getPhone(),
                restaurant.getEmail(),
                restaurant.getAddress()
        );
    }
}
