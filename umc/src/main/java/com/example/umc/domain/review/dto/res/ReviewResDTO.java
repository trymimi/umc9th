package com.example.umc.domain.review.dto.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public class ReviewResDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReviewPreViewDTO {
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

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReviewPreViewListDTO {
        private List<ReviewPreViewDTO> reviews;
        private int currentPage;
        private int totalPages;
        private long totalElements;
    }
}

