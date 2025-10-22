package org.project.vibgyor.repository;

import org.apache.catalina.User;
import org.project.vibgyor.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    List<Order> findByOrderStatus(String orderStatus);
    List<Order> findByStatusTrue();
    User findByEmail(String email);
}
