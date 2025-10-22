package org.project.vibgyor.repository;

import org.project.vibgyor.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    List<Cart> findByCustomer_UserId(Integer customerId);
}
