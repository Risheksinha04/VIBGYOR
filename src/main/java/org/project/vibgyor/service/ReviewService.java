package org.project.vibgyor.service;


import org.apache.catalina.User;
import org.project.vibgyor.entity.Product;
import org.project.vibgyor.entity.Review;
import org.project.vibgyor.repository.ProductRepository;
import org.project.vibgyor.repository.ReviewRepository;
import org.project.vibgyor.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Review> getReviewsByProduct(Integer productId) {
        return reviewRepository.findByProduct_ProductIdAndStatusTrue(productId);
    }

    public Review addReview(Integer productId, Integer customerId, Integer rating, String reviewText) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        User customer = userRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        Review review = new Review();
        review.setProduct(product);
        review.setCustomer(customer);
        review.setRating(rating);
        review.setReviewText(reviewText);
        review.setCreatedAt(LocalDateTime.now());
        review.setUpdatedAt(LocalDateTime.now());
        review.setStatus(false); // set unapproved by default
        return reviewRepository.save(review);
    }

    public List<Review> getPendingReviews() {
        return reviewRepository.findByStatusFalse();
    }

    public Review moderateReview(Integer reviewId, Boolean approve) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("Review not found"));
        review.setStatus(approve);
        review.setUpdatedAt(LocalDateTime.now());
        return reviewRepository.save(review);
    }

    public Review updateReview(Integer reviewId, String reviewText, Integer rating) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("Review not found"));
        if (reviewText != null) review.setReviewText(reviewText);
        if (rating != null) review.setRating(rating);
        review.setUpdatedAt(LocalDateTime.now());
        return reviewRepository.save(review);
    }

    public void deleteReview(Integer reviewId) {
        reviewRepository.deleteById(reviewId);
    }
}
