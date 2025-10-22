package org.project.vibgyor.controller;

import org.project.vibgyor.entity.Wishlist;
import org.project.vibgyor.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wishlists")
public class WishlistController {
    @Autowired
    private WishlistService wishlistService;

    @GetMapping("/customer/{customerId}")
    public List<Wishlist> getWishlistByCustomer(@PathVariable Integer customerId) {
        return wishlistService.getWishlistByCustomer(customerId);
    }

    @PostMapping("/add")
    public Wishlist addToWishlist(@RequestParam Integer customerId, @RequestParam Integer productId) {
        return wishlistService.addToWishlist(customerId, productId);
    }

    @DeleteMapping("/remove/{wishlistId}")
    public void removeFromWishlist(@PathVariable Integer wishlistId) {
        wishlistService.removeFromWishlist(wishlistId);
    }

    @PostMapping("/move-to-cart/{wishlistId}")
    public Wishlist moveToCart(@PathVariable Integer wishlistId, @RequestParam Integer quantity) {
        return wishlistService.moveToCart(wishlistId, quantity);
    }
}
