package com.example.demo.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum OrderStatus {
    PENDING("pending"),
    CONFIRMED("confirmed"),
    CANCELLED("cancelled"),
    DELIVERED("delivered");

    private final String value;

    OrderStatus(String value) {
        this.value = value;
    }

    @JsonCreator
    public static OrderStatus fromValue(String value) {
        for (OrderStatus status : OrderStatus.values()) {
            if (status.value.equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Geçersiz OrderStatus: " + value);
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
