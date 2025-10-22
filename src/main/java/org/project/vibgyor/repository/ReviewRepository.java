package org.project.vibgyor.repository;

import org.project.vibgyor.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    List<Review> findByProduct_ProductIdAndStatusTrue(Integer productId);
    List<Review> findByStatusFalse();
}
