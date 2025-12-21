package com.example.umc.domain.mission.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MissionResponseDto {
    private Long missionId;
    private Long storeId;
    private String description;
    private Integer point;
    private String status;
}

