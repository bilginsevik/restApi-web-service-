package com.example.demo.service.impl;

import com.example.demo.dto.OrderItemRequest;
import com.example.demo.dto.OrderRequestDTO;
import com.example.demo.exception.*;
import com.example.demo.model.*;
import com.example.demo.model.enums.OrderStatus;
import com.example.demo.repository.*;
import com.example.demo.service.IOrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements IOrderService {

    private final OrderRepository orderRepository;
    private final BranchRepository branchRepository;
    private final UserRepository userRepository;
    private final MenuItemRepository menuItemRepository;
    private final OrderItemRepository orderItemRepository;


    private static final LocalTime OPEN_TIME = LocalTime.of(9, 0);
    private static final LocalTime CLOSE_TIME = LocalTime.of(22, 0);

    public OrderServiceImpl(OrderRepository orderRepository,
                            BranchRepository branchRepository,
                            UserRepository userRepository,
                            MenuItemRepository menuItemRepository,
                            OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.branchRepository = branchRepository;
        this.userRepository = userRepository;
        this.menuItemRepository = menuItemRepository;
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    @Transactional
    public Order createOrder(OrderRequestDTO orderRequest) {
        Branch branch = branchRepository.findById(orderRequest.getBranchId())
                .orElseThrow(() -> new BranchNotFoundException("Branch bulunamadı"));

        Users user = userRepository.findById(orderRequest.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User bulunamadı"));


        LocalTime now = LocalTime.now();


        if (now.isBefore(OPEN_TIME) || now.isAfter(CLOSE_TIME)) {
            throw new RestaurantClosedException("Restoran çalışma saatleri dışındasınız. Açılış saati: " + OPEN_TIME + ", Kapanış saati: " + CLOSE_TIME);
        }

        OrderStatus status;
        try {
            status = OrderStatus.fromValue(orderRequest.getStatus());
        } catch (IllegalArgumentException e) {
            throw new InvalidOrderStatusException("Geçersiz order status: " + orderRequest.getStatus());
        }

        if (orderRequest.getOrderItems() == null || orderRequest.getOrderItems().isEmpty()) {
            throw new OrderItemsEmptyException("Order items listesi boş olamaz");
        }

        Order order = new Order();
        order.setBranch(branch);
        order.setUser(user);
        order.setStatus(status);
        order.setOrderTime(LocalDateTime.now());

        List<OrderItem> orderItems = new ArrayList<>();
        BigDecimal totalPrice = BigDecimal.ZERO;

        for (OrderItemRequest itemRequest : orderRequest.getOrderItems()) {
            MenuItem menuItem = menuItemRepository.findById(itemRequest.getMenuItemId())
                    .orElseThrow(() -> new MenuItemNotFoundException("MenuItem bulunamadı: " + itemRequest.getMenuItemId()));

            OrderItem orderItem = new OrderItem();
            orderItem.setMenuItem(menuItem);
            orderItem.setQuantity(itemRequest.getQuantity());

            BigDecimal priceForItem = menuItem.getPrice().multiply(BigDecimal.valueOf(itemRequest.getQuantity()));
            orderItem.setPrice(priceForItem);
            orderItem.setOrder(order);

            totalPrice = totalPrice.add(priceForItem);
            orderItems.add(orderItem);
        }

        if (totalPrice.compareTo(new BigDecimal("500")) < 0) {
            throw new MinimumOrderAmountException("Toplam sipariş tutarı en az 500 TL olmalıdır.");
        }

        order.setTotalPrice(totalPrice);
        order.setOrderItems(orderItems);

        return orderRepository.save(order);
    }

    @Override
    public Order updateStatus(Long orderId, OrderStatus newStatus) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Sipariş bulunamadı"));
        order.setStatus(newStatus);
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
