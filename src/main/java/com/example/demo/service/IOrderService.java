package com.example.demo.service;

import com.example.demo.dto.OrderRequestDTO;
import com.example.demo.model.Order;
import com.example.demo.model.enums.OrderStatus;

import java.util.List;

public interface IOrderService {

    Order createOrder(OrderRequestDTO orderRequest);

    Order updateStatus(Long orderId, OrderStatus newStatus);

    List<Order> getAllOrders();
}
