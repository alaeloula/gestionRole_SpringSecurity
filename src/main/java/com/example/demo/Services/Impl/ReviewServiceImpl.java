package com.example.demo.Services.Impl;


import com.example.demo.Services.ReviewService;
import com.example.demo.configuration.SpringSecurityConfig;
import com.example.demo.model.Review;
import com.example.demo.repository.DBUserRepository;
import com.example.demo.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final DBUserRepository userRepository;
    public ReviewServiceImpl(ReviewRepository reviewRepository, DBUserRepository userRepository){
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Review saveReview(Review review) {

        var user = userRepository.findByUsername(
                SpringSecurityConfig.getSessionUser()
        ).get();
        review.setUser(user);
        review.setDate(LocalDate.now());
        return reviewRepository.save(review);
    }

    @Override
    public Review getReviewById(UUID id) {
        return reviewRepository.findById(id).orElse(null);
    }

    @Override
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    @Override
    public void deleteReview(UUID id) {
        reviewRepository.deleteById(id);
    }
}
