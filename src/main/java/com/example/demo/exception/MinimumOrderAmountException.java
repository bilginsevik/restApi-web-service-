package com.example.demo.exception;

public class MinimumOrderAmountException extends RuntimeException {
    public MinimumOrderAmountException(String message) {
        super(message);
    }
}
