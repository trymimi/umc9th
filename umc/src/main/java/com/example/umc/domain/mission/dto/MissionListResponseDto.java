package com.example.umc.domain.mission.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MissionListResponseDto {
    private List<MissionResponseDto> missions;
    private int currentPage;
    private int totalPages;
    private long totalElements;
}

