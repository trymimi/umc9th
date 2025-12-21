package com.example.umc.domain.review.service;

import com.example.umc.domain.review.converter.ReviewConverter;
import com.example.umc.domain.review.dto.res.ReviewResDTO;
import com.example.umc.domain.review.entity.Review;
import com.example.umc.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewResDTO.ReviewPreViewListDTO getReviews(Long storeId, String storeName, Integer minRating, Integer maxRating, Pageable pageable) {
        Page<Review> reviewPage = reviewRepository.findReviewsWithFilters(storeId, storeName, minRating, maxRating, pageable);
        return ReviewConverter.toReviewPreViewListDTO(reviewPage);
    }

    public ReviewResDTO.ReviewPreViewListDTO getMyReviews(Long memberId, Pageable pageable) {
        Page<Review> reviewPage = reviewRepository.findByMemberIdOrderByIdDesc(memberId, pageable);
        return ReviewConverter.toReviewPreViewListDTO(reviewPage);
    }
}

