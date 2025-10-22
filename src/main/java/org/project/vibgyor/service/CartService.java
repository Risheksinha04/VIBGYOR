package org.project.vibgyor.service;

import org.apache.catalina.User;
import org.project.vibgyor.entity.Cart;
import org.project.vibgyor.entity.Product;
import org.project.vibgyor.repository.CartRepository;
import org.project.vibgyor.repository.ProductRepository;
import org.project.vibgyor.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Cart> getCartByCustomer(Integer customerId) {
        return cartRepository.findByCustomer_UserId(customerId);
    }

    public Cart addToCart(Integer customerId, Integer productId, Integer quantity) {
        User customer = userRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        if (product.getInventoryCount() < quantity) {
            throw new RuntimeException("Insufficient stock");
        }
        Cart cart = new Cart();
        cart.setCustomer(customer);
        cart.setProduct(product);
        cart.setQuantity(quantity);
        cart.setTotalPrice(product.getPrice() * quantity);
        cart.setCreatedAt(LocalDateTime.now());
        cart.setUpdatedAt(LocalDateTime.now());
        return cartRepository.save(cart);
    }

    public Cart updateCart(Integer cartId, Integer quantity) {
        Optional<Cart> optionalCart = cartRepository.findById(cartId);
        if (optionalCart.isPresent()) {
            Cart cart = optionalCart.get();
            Product product = cart.getProduct();
            if (product.getInventoryCount() < quantity) {
                throw new RuntimeException("Insufficient stock");
            }
            cart.setQuantity(quantity);
            cart.setTotalPrice(product.getPrice() * quantity);
            cart.setUpdatedAt(LocalDateTime.now());
            return cartRepository.save(cart);
        }
        throw new RuntimeException("Cart not found");
    }

    public void removeFromCart(Integer cartId) {
        cartRepository.deleteById(cartId);
    }
}
