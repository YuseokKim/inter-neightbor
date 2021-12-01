package com.inter.neightbor.service;

import com.inter.neightbor.domain.Order;
import com.inter.neightbor.domain.Review;
import com.inter.neightbor.dto.OrderRequestDto;
import com.inter.neightbor.dto.ReviewRequestDto;
import com.inter.neightbor.repository.ReviewRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public Flux<Review> findAll() {
        return reviewRepository.findAll();
    }

    public Mono<Review> findById(String id) {
        return reviewRepository.findById(id);
    }

    public Mono<Review> createReview(ReviewRequestDto reviewRequestDto) {
        Review review = new Review(reviewRequestDto);
        return reviewRepository.insert(review);
    }
}
