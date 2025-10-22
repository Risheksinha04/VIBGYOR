package org.project.vibgyor.repository;

import org.project.vibgyor.entity.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WishlistRepository extends JpaRepository<Wishlist, Integer> {
    List<Wishlist> findByCustomer_UserId(Integer customerId);
}
