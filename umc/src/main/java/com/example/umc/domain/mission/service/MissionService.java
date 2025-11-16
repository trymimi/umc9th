package com.example.umc.domain.mission.service;

import com.example.umc.domain.mission.dto.MissionListResponseDto;
import com.example.umc.domain.mission.dto.MissionResponseDto;
import com.example.umc.domain.mission.entity.Mission;
import com.example.umc.domain.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionService {

    private final MissionRepository missionRepository;

    public MissionListResponseDto getMissions(Long storeId, String status, Pageable pageable) {
        com.example.umc.domain.mission.enums.MissionStatus missionStatus = null;
        if (status != null && !status.isEmpty()) {
            try {
                missionStatus = com.example.umc.domain.mission.enums.MissionStatus.valueOf(status.toUpperCase());
            } catch (IllegalArgumentException e) {
                missionStatus = null;
            }
        }

        Page<Mission> missionPage = missionRepository.findMissionsWithFilters(storeId, missionStatus, pageable);

        List<MissionResponseDto> missionDtos = missionPage.getContent().stream()
                .map(mission -> MissionResponseDto.builder()
                        .missionId(mission.getId())
                        .storeId(mission.getStore().getId())
                        .description(mission.getDescription())
                        .point(mission.getPoint())
                        .status(mission.getStatus().name())
                        .build())
                .collect(Collectors.toList());

        return MissionListResponseDto.builder()
                .missions(missionDtos)
                .currentPage(missionPage.getNumber())
                .totalPages(missionPage.getTotalPages())
                .totalElements(missionPage.getTotalElements())
                .build();
    }
}

