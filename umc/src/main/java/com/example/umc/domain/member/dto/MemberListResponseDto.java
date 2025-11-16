package com.example.umc.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberListResponseDto {
    private List<MemberResponseDto> members;
    private int currentPage;
    private int totalPages;
    private long totalElements;
}

