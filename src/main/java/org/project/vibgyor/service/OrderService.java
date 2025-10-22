package org.project.vibgyor.service;


import org.project.vibgyor.entity.Order;
import org.project.vibgyor.repository.OrderRepository;
import org.project.vibgyor.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    public OrderRepository getOrderRepository() {
        return orderRepository;
    }

    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<Order> getAllActiveOrders() {
        return orderRepository.findByStatusTrue();
    }

    public Order createOrder(Order order) {
        order.setCreatedAt(LocalDateTime.now());
        order.setStatus(true);
        order.setCustomer(userRepository.findById(order.getCustomer().getUserId())
                .orElseThrow(() -> new RuntimeException("User not found")));
        return orderRepository.save(order);
    }

    public Order updateOrderStatus(Integer id, String status) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            order.setOrderStatus(status);
            order.setUpdatedAt(LocalDateTime.now());
            return orderRepository.save(order);
        }
        throw new RuntimeException("Order not found");
    }

    public void cancelOrder(Integer id) {
        orderRepository.findById(id).ifPresent(order -> {
            order.setStatus(false);
            order.setOrderStatus("Cancelled");
            order.setUpdatedAt(LocalDateTime.now());
            orderRepository.save(order);
        });
    }
}

