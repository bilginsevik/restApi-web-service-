package com.example.demo.service.impl;

import com.example.demo.dto.OrderItemRequest;
import com.example.demo.dto.OrderItemResponse;
import com.example.demo.model.MenuItem;
import com.example.demo.model.OrderItem;
import com.example.demo.repository.MenuItemRepository;
import com.example.demo.repository.OrderItemRepository;
import com.example.demo.service.IOrderItemService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderItemServiceImpl implements IOrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final MenuItemRepository menuItemRepository;
    private final ModelMapper modelMapper;

    public OrderItemServiceImpl(OrderItemRepository orderItemRepository,
                                MenuItemRepository menuItemRepository,
                                ModelMapper modelMapper) {
        this.orderItemRepository = orderItemRepository;
        this.menuItemRepository = menuItemRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public OrderItemResponse createOrderItem(OrderItemRequest request) {
        OrderItem orderItem = new OrderItem();
        orderItem.setQuantity(request.getQuantity());

        MenuItem menuItem = menuItemRepository.findById(request.getMenuItemId())
                .orElseThrow(() -> new RuntimeException("MenuItem not found with id: " + request.getMenuItemId()));

        orderItem.setMenuItem(menuItem);

        // Fiyatı menü öğesinden alabiliriz
        orderItem.setPrice(menuItem.getPrice().multiply(BigDecimal.valueOf(request.getQuantity())));

        OrderItem saved = orderItemRepository.save(orderItem);
        return modelMapper.map(saved, OrderItemResponse.class);
    }

    @Override
    public List<OrderItemResponse> getAllOrderItems() {
        List<OrderItem> items = orderItemRepository.findAll();
        return items.stream()
                .map(item -> modelMapper.map(item, OrderItemResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public OrderItemResponse getOrderItemById(Long id) {
        OrderItem orderItem = orderItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order item not found with id: " + id));
        return modelMapper.map(orderItem, OrderItemResponse.class);
    }

    @Override
    public OrderItemResponse updateOrderItem(Long id, OrderItemRequest updatedItem) {
        return orderItemRepository.findById(id)
                .map(item -> {
                    item.setQuantity(updatedItem.getQuantity());

                    MenuItem menuItem = menuItemRepository.findById(updatedItem.getMenuItemId())
                            .orElseThrow(() -> new RuntimeException("MenuItem not found with id: " + updatedItem.getMenuItemId()));

                    item.setMenuItem(menuItem);
                    item.setPrice(menuItem.getPrice().multiply(BigDecimal.valueOf(updatedItem.getQuantity())));

                    OrderItem saved = orderItemRepository.save(item);
                    return modelMapper.map(saved, OrderItemResponse.class);
                })
                .orElseThrow(() -> new RuntimeException("Order item not found with id: " + id));
    }

    @Override
    public void deleteOrderItem(Long id) {
        orderItemRepository.deleteById(id);
    }
}
