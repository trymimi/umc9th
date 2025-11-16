package com.example.umc.domain.mapping.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberMissionListResponseDto {
    private List<MemberMissionResponseDto> memberMissions;
    private int currentPage;
    private int totalPages;
    private long totalElements;
}

