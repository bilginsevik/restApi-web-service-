package com.example.demo.service;

import com.example.demo.dto.OrderItemRequest;
import com.example.demo.dto.OrderItemResponse;

import java.util.List;

public interface IOrderItemService {

    OrderItemResponse createOrderItem(OrderItemRequest request);

    List<OrderItemResponse> getAllOrderItems();

    OrderItemResponse getOrderItemById(Long id);

    OrderItemResponse updateOrderItem(Long id, OrderItemRequest updatedItem);

    void deleteOrderItem(Long id);
}
