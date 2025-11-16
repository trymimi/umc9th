package com.example.umc.domain.review.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewListResponseDto {
    private List<ReviewResponseDto> reviews;
    private int currentPage;
    private int totalPages;
    private long totalElements;
}

