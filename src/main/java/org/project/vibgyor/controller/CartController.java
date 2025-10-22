package org.project.vibgyor.controller;

import org.project.vibgyor.entity.Cart;
import org.project.vibgyor.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carts")
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping("/customer/{customerId}")
    public List<Cart> getCartByCustomer(@PathVariable Integer customerId) {
        return cartService.getCartByCustomer(customerId);
    }

    @PostMapping("/add")
    public Cart addToCart(@RequestParam Integer customerId, @RequestParam Integer productId, @RequestParam Integer quantity) {
        return cartService.addToCart(customerId, productId, quantity);
    }

    @PutMapping("/update/{cartId}")
    public Cart updateCart(@PathVariable Integer cartId, @RequestParam Integer quantity) {
        return cartService.updateCart(cartId, quantity);
    }

    @DeleteMapping("/remove/{cartId}")
    public void removeFromCart(@PathVariable Integer cartId) {
        cartService.removeFromCart(cartId);
    }
}
