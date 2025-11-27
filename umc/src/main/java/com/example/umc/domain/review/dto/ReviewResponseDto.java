package com.example.umc.domain.review.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewResponseDto {
    private Long reviewId;
    private Long storeId;
    private String storeName;
    private Long memberId;
    private String memberName;  // 닉네임
    private Float rating;
    private String content;
    private String photo;
    private LocalDateTime createdAt;  // 작성 날짜
}

