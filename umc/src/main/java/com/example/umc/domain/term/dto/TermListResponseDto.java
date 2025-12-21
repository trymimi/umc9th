package com.example.umc.domain.term.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TermListResponseDto {
    private List<TermResponseDto> terms;
    private int currentPage;
    private int totalPages;
    private long totalElements;
}

