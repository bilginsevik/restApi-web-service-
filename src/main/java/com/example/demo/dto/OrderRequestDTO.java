package com.example.demo.dto;

import java.math.BigDecimal;
import java.util.List;

public class OrderRequestDTO {
    private Long userId;
    private Long branchId;
    private String status;
    private BigDecimal totalPrice;
    private List<OrderItemRequest> orderItems;

    // Getter-Setter
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getBranchId() {
        return branchId;
    }
    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<OrderItemRequest> getOrderItems() {
        return orderItems;
    }
    public void setOrderItems(List<OrderItemRequest> orderItems) {
        this.orderItems = orderItems;
    }
}
