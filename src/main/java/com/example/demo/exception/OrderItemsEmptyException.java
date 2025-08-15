package com.example.demo.exception;

public class OrderItemsEmptyException extends RuntimeException {
    public OrderItemsEmptyException(String message) {
        super(message);
    }
}
