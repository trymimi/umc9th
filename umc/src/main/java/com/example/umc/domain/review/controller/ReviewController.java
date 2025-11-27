package com.example.umc.domain.review.controller;

import com.example.umc.domain.review.dto.res.ReviewResDTO;
import com.example.umc.domain.review.service.ReviewService;
import com.example.umc.global.annotation.ValidPage;
import com.example.umc.global.apiPayload.ApiResponse;
import com.example.umc.global.apiPayload.code.GeneralSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "리뷰", description = "리뷰 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @Operation(summary = "리뷰 목록 조회", description = "조건에 맞는 리뷰 목록을 페이징하여 조회합니다.")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "조회 성공")
    })
    @GetMapping
    public ApiResponse<ReviewResDTO.ReviewPreViewListDTO> getReviews(
            @Parameter(description = "가게 ID") @RequestParam(required = false) Long storeId,
            @Parameter(description = "가게 이름") @RequestParam(required = false) String storeName,
            @Parameter(description = "평점") @RequestParam(required = false) Integer rating,
            @Parameter(description = "페이지 번호 (1 이상)") @ValidPage @RequestParam int page,
            @Parameter(description = "페이지 크기") @RequestParam(defaultValue = "10") int size) {

        Integer minRating = null;
        Integer maxRating = null;
        if (rating != null) {
            minRating = rating;
            if (rating < 5) {
                maxRating = rating + 1;
            }
        }

        Pageable pageable = PageRequest.of(page - 1, size);
        ReviewResDTO.ReviewPreViewListDTO response = reviewService.getReviews(storeId, storeName, minRating, maxRating, pageable);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, response);
    }

    @Operation(summary = "내가 작성한 리뷰 목록 조회", description = "특정 회원이 작성한 리뷰 목록을 페이징하여 조회합니다.")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "조회 성공")
    })
    @GetMapping("/members/{memberId}")
    public ApiResponse<ReviewResDTO.ReviewPreViewListDTO> getMyReviews(
            @Parameter(description = "회원 ID") @PathVariable Long memberId,
            @Parameter(description = "페이지 번호 (1 이상)") @ValidPage @RequestParam int page,
            @Parameter(description = "페이지 크기") @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page - 1, size);
        ReviewResDTO.ReviewPreViewListDTO response = reviewService.getMyReviews(memberId, pageable);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, response);
    }
}

