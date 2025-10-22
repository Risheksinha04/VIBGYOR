package org.project.vibgyor.service;

import org.project.vibgyor.entity.Product;
import org.project.vibgyor.repository.CategoryRepository;
import org.project.vibgyor.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Product> getAllActiveProducts() {
        return productRepository.findByStatusTrue();
    }

    public Product createProduct(Product product) {
        product.setCreatedAt(LocalDateTime.now());
        product.setStatus(true);
        product.setCategory(categoryRepository.findById(product.getCategory().getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found")));
        return productRepository.save(product);
    }

    public Product updateProduct(Integer id, Product updatedProduct) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setProductName(updatedProduct.getProductName());
            product.setDescription(updatedProduct.getDescription());
            product.setPrice(updatedProduct.getPrice());
            product.setSku(updatedProduct.getSku());
            if (updatedProduct.getCategory() != null)
                product.setCategory(categoryRepository.findById(updatedProduct.getCategory().getCategoryId())
                        .orElse(product.getCategory()));
            product.setInventoryCount(updatedProduct.getInventoryCount());
            product.setUpdatedAt(LocalDateTime.now());
            return productRepository.save(product);
        }
        throw new RuntimeException("Product not found");
    }

    public void deactivateProduct(Integer id) {
        productRepository.findById(id).ifPresent(product -> {
            product.setStatus(false);
            product.setUpdatedAt(LocalDateTime.now());
            productRepository.save(product);
        });
    }
}
