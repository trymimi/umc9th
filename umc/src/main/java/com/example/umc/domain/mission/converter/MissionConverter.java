package com.example.umc.domain.mission.converter;

import com.example.umc.domain.mission.dto.MissionListResponseDto;
import com.example.umc.domain.mission.dto.MissionResponseDto;
import com.example.umc.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class MissionConverter {

    public static MissionResponseDto toMissionResponseDto(Mission mission) {
        return MissionResponseDto.builder()
                .missionId(mission.getId())
                .storeId(mission.getStore().getId())
                .description(mission.getDescription())
                .point(mission.getPoint())
                .status(mission.getStatus().name())
                .build();
    }

    public static MissionListResponseDto toMissionListResponseDto(Page<Mission> missionPage) {
        List<MissionResponseDto> missionDtos = missionPage.getContent().stream()
                .map(MissionConverter::toMissionResponseDto)
                .collect(Collectors.toList());

        return MissionListResponseDto.builder()
                .missions(missionDtos)
                .currentPage(missionPage.getNumber())
                .totalPages(missionPage.getTotalPages())
                .totalElements(missionPage.getTotalElements())
                .build();
    }
}

