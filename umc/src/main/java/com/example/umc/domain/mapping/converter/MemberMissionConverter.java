package com.example.umc.domain.mapping.converter;

import com.example.umc.domain.mapping.dto.MemberMissionListResponseDto;
import com.example.umc.domain.mapping.dto.MemberMissionResponseDto;
import com.example.umc.domain.mapping.entity.MemberMission;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class MemberMissionConverter {

    public static MemberMissionResponseDto toMemberMissionResponseDto(MemberMission memberMission) {
        return MemberMissionResponseDto.builder()
                .memberId(memberMission.getMember().getId())
                .missionId(memberMission.getMission().getId())
                .status(memberMission.getStatus().name())
                .build();
    }

    public static MemberMissionListResponseDto toMemberMissionListResponseDto(Page<MemberMission> memberMissionPage) {
        List<MemberMissionResponseDto> memberMissionDtos = memberMissionPage.getContent().stream()
                .map(MemberMissionConverter::toMemberMissionResponseDto)
                .collect(Collectors.toList());

        return MemberMissionListResponseDto.builder()
                .memberMissions(memberMissionDtos)
                .currentPage(memberMissionPage.getNumber())
                .totalPages(memberMissionPage.getTotalPages())
                .totalElements(memberMissionPage.getTotalElements())
                .build();
    }
}

