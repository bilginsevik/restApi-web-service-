package com.example.demo.controller;

import com.example.demo.dto.OrderItemRequest;
import com.example.demo.dto.OrderItemResponse;
import com.example.demo.service.IOrderItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order-items")
public class OrderItemController {

    private final IOrderItemService orderItemService;

    public OrderItemController(IOrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @PostMapping
    public OrderItemResponse createOrderItem(@RequestBody OrderItemRequest request) {
        return orderItemService.createOrderItem(request);
    }

    @GetMapping
    public List<OrderItemResponse> getAllOrderItems() {
        return orderItemService.getAllOrderItems();
    }

    @GetMapping("/{id}")
    public OrderItemResponse getOrderItemById(@PathVariable Long id) {
        return orderItemService.getOrderItemById(id);
    }

    @PutMapping("/{id}")
    public OrderItemResponse updateOrderItem(@PathVariable Long id, @RequestBody OrderItemRequest request) {
        return orderItemService.updateOrderItem(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteOrderItem(@PathVariable Long id) {
        orderItemService.deleteOrderItem(id);
    }
}
