package com.example.umc.domain.review.converter;

import com.example.umc.domain.review.dto.res.ReviewResDTO;
import com.example.umc.domain.review.entity.Review;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class ReviewConverter {

    public static ReviewResDTO.ReviewPreViewDTO toReviewPreViewDTO(Review review) {
        return ReviewResDTO.ReviewPreViewDTO.builder()
                .reviewId(review.getId())
                .storeId(review.getStore().getId())
                .storeName(review.getStore().getName())
                .memberId(review.getMember().getId())
                .memberName(review.getMember().getName())  // 닉네임
                .rating(review.getRating())
                .content(review.getContent())
                .photo(review.getPhoto())
                .createdAt(review.getCreatedAt())  // 작성 날짜
                .build();
    }

    public static ReviewResDTO.ReviewPreViewListDTO toReviewPreViewListDTO(Page<Review> reviewPage) {
        List<ReviewResDTO.ReviewPreViewDTO> reviewDtos = reviewPage.getContent().stream()
                .map(ReviewConverter::toReviewPreViewDTO)
                .collect(Collectors.toList());

        return ReviewResDTO.ReviewPreViewListDTO.builder()
                .reviews(reviewDtos)
                .currentPage(reviewPage.getNumber())
                .totalPages(reviewPage.getTotalPages())
                .totalElements(reviewPage.getTotalElements())
                .build();
    }
}

