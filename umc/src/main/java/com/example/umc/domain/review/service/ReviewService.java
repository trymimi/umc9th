package com.example.umc.domain.review.service;

import com.example.umc.domain.review.dto.ReviewListResponseDto;
import com.example.umc.domain.review.dto.ReviewResponseDto;
import com.example.umc.domain.review.entity.Review;
import com.example.umc.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewListResponseDto getReviews(Long storeId, String storeName, Integer minRating, Integer maxRating, Pageable pageable) {
        Page<Review> reviewPage = reviewRepository.findReviewsWithFilters(storeId, storeName, minRating, maxRating, pageable);

        List<ReviewResponseDto> reviewDtos = reviewPage.getContent().stream()
                .map(review -> ReviewResponseDto.builder()
                        .reviewId(review.getId())
                        .storeId(review.getStore().getId())
                        .storeName(review.getStore().getName())
                        .memberId(review.getMember().getId())
                        .rating(review.getRating())
                        .content(review.getContent())
                        .photo(review.getPhoto())
                        .build())
                .collect(Collectors.toList());

        return ReviewListResponseDto.builder()
                .reviews(reviewDtos)
                .currentPage(reviewPage.getNumber())
                .totalPages(reviewPage.getTotalPages())
                .totalElements(reviewPage.getTotalElements())
                .build();
    }
}

