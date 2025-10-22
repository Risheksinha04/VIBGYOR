package org.project.vibgyor.repository;

import org.project.vibgyor.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByStatusTrue();
    List<Product> findByCategory_CategoryIdAndStatusTrue(Integer categoryId);
}
