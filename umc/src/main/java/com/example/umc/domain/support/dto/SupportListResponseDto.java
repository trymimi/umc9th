package com.example.umc.domain.support.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SupportListResponseDto {
    private List<SupportResponseDto> supports;
    private int currentPage;
    private int totalPages;
    private long totalElements;
}

