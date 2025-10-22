package org.project.vibgyor.repository;

import org.project.vibgyor.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByOrderStatus(String orderStatus);
    List<Order> findByStatusTrue();
}
