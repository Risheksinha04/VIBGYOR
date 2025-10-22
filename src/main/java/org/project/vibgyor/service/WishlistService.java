package org.project.vibgyor.service;

import org.apache.catalina.User;
import org.project.vibgyor.entity.Product;
import org.project.vibgyor.entity.Wishlist;
import org.project.vibgyor.repository.ProductRepository;
import org.project.vibgyor.repository.UserRepository;
import org.project.vibgyor.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class WishlistService
{
    @Autowired
    private WishlistRepository wishlistRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Wishlist> getWishlistByCustomer(Integer customerId) {
        return wishlistRepository.findByCustomer_UserId(customerId);
    }

    public Wishlist addToWishlist(Integer customerId, Integer productId) {
        User customer = userRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        Wishlist wishlist = new Wishlist();
        wishlist.setCustomer(customer);
        wishlist.setProduct(product);
        wishlist.setCreatedAt(LocalDateTime.now());
        wishlist.setUpdatedAt(LocalDateTime.now());
        return wishlistRepository.save(wishlist);
    }

    public void removeFromWishlist(Integer wishlistId) {
        wishlistRepository.deleteById(wishlistId);
    }

    public Wishlist moveToCart(Integer wishlistId, Integer quantity) {
        Wishlist wishlist = wishlistRepository.findById(wishlistId)
                .orElseThrow(() -> new RuntimeException("Wishlist entry not found"));
        wishlistRepository.delete(wishlist);
        return wishlist;
    }
}
