package org.project.vibgyor.controller;

import org.project.vibgyor.entity.Review;
import org.project.vibgyor.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @GetMapping("/product/{productId}")
    public List<Review> getReviewsByProduct(@PathVariable Integer productId) {
        return reviewService.getReviewsByProduct(productId);
    }

    @PostMapping("/add")
    public Review addReview(@RequestParam Integer productId,
                            @RequestParam Integer customerId,
                            @RequestParam Integer rating,
                            @RequestParam String reviewText) {
        return reviewService.addReview(productId, customerId, rating, reviewText);
    }

    @GetMapping("/pending")
    public List<Review> getPendingReviews() {
        return reviewService.getPendingReviews();
    }

    @PutMapping("/moderate/{reviewId}")
    public Review moderateReview(@PathVariable Integer reviewId, @RequestParam Boolean approve) {
        return reviewService.moderateReview(reviewId, approve);
    }

    @PutMapping("/update/{reviewId}")
    public Review updateReview(@PathVariable Integer reviewId,
                               @RequestParam(required = false) String reviewText,
                               @RequestParam(required = false) Integer rating) {
        return reviewService.updateReview(reviewId, reviewText, rating);
    }

    @DeleteMapping("/delete/{reviewId}")
    public void deleteReview(@PathVariable Integer reviewId) {
        reviewService.deleteReview(reviewId);
    }
}
