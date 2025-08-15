package com.example.demo.controller;

import com.example.demo.dto.OrderRequestDTO;
import com.example.demo.dto.OrderStatusDTO;
import com.example.demo.model.Order;
import com.example.demo.model.enums.OrderStatus;
import com.example.demo.service.IOrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final IOrderService orderService;

    public OrderController(IOrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequestDTO dto) {
        Order createdOrder = orderService.createOrder(dto);
        return ResponseEntity.ok(createdOrder);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Order> updateOrderStatus(@PathVariable Long id, @RequestBody OrderStatusDTO statusDTO) {
        OrderStatus status = OrderStatus.valueOf(statusDTO.getStatus().toUpperCase());
        Order updatedOrder = orderService.updateStatus(id, status);
        return ResponseEntity.ok(updatedOrder);
    }
}
