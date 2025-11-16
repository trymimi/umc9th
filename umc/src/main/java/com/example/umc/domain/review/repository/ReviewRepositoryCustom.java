package com.example.umc.domain.review.repository;

import com.example.umc.domain.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewRepositoryCustom {
    Page<Review> findReviewsWithFilters(Long storeId, String storeName, Integer minRating, Integer maxRating, Pageable pageable);
}

