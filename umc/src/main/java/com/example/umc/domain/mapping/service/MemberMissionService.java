package com.example.umc.domain.mapping.service;

import com.example.umc.domain.mapping.dto.MemberMissionListResponseDto;
import com.example.umc.domain.mapping.dto.MemberMissionResponseDto;
import com.example.umc.domain.mapping.entity.MemberMission;
import com.example.umc.domain.mapping.repository.MemberMissionRepository;
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
public class MemberMissionService {

    private final MemberMissionRepository memberMissionRepository;

    public MemberMissionListResponseDto getMemberMissions(Long memberId, String status, Pageable pageable) {
        com.example.umc.domain.mapping.enums.MemberMissionStatus memberMissionStatus = null;
        if (status != null && !status.isEmpty()) {
            try {
                memberMissionStatus = com.example.umc.domain.mapping.enums.MemberMissionStatus.valueOf(status.toUpperCase());
            } catch (IllegalArgumentException e) {
                memberMissionStatus = null;
            }
        }

        Page<MemberMission> memberMissionPage = memberMissionRepository.findMemberMissionsWithFilters(memberId, memberMissionStatus, pageable);

        List<MemberMissionResponseDto> memberMissionDtos = memberMissionPage.getContent().stream()
                .map(memberMission -> MemberMissionResponseDto.builder()
                        .memberId(memberMission.getMember().getId())
                        .missionId(memberMission.getMission().getId())
                        .status(memberMission.getStatus().name())
                        .build())
                .collect(Collectors.toList());

        return MemberMissionListResponseDto.builder()
                .memberMissions(memberMissionDtos)
                .currentPage(memberMissionPage.getNumber())
                .totalPages(memberMissionPage.getTotalPages())
                .totalElements(memberMissionPage.getTotalElements())
                .build();
    }
}

