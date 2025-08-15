package com.example.demo.exception;

public class RestaurantClosedException extends RuntimeException {
    public RestaurantClosedException(String message) {
        super(message);
    }
}
