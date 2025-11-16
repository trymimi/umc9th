package com.example.umc.domain.review.controller;

import com.example.umc.domain.review.dto.ReviewListResponseDto;
import com.example.umc.domain.review.service.ReviewService;
import com.example.umc.global.apiPayload.ApiResponse;
import com.example.umc.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping
    public ApiResponse<ReviewListResponseDto> getReviews(
            @RequestParam(required = false) Long storeId,
            @RequestParam(required = false) String storeName,
            @RequestParam(required = false) Integer rating,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Integer minRating = null;
        Integer maxRating = null;
        if (rating != null) {
            minRating = rating;
            if (rating < 5) {
                maxRating = rating + 1;
            }
        }

        Pageable pageable = PageRequest.of(page, size);
        ReviewListResponseDto response = reviewService.getReviews(storeId, storeName, minRating, maxRating, pageable);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, response);
    }
}

