package com.example.umc.domain.mapping.service;

import com.example.umc.domain.mapping.converter.MemberMissionConverter;
import com.example.umc.domain.mapping.dto.MemberMissionListResponseDto;
import com.example.umc.domain.mapping.entity.MemberMission;
import com.example.umc.domain.mapping.enums.MemberMissionStatus;
import com.example.umc.domain.mapping.repository.MemberMissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberMissionService {

    private final MemberMissionRepository memberMissionRepository;

    public MemberMissionListResponseDto getMemberMissions(Long memberId, String status, Pageable pageable) {
        MemberMissionStatus memberMissionStatus = null;
        if (status != null && !status.isEmpty()) {
            try {
                memberMissionStatus = MemberMissionStatus.valueOf(status.toUpperCase());
            } catch (IllegalArgumentException e) {
                memberMissionStatus = null;
            }
        }

        Page<MemberMission> memberMissionPage = memberMissionRepository.findMemberMissionsWithFilters(memberId, memberMissionStatus, pageable);
        return MemberMissionConverter.toMemberMissionListResponseDto(memberMissionPage);
    }

    public MemberMissionListResponseDto getMyOngoingMissions(Long memberId, Pageable pageable) {
        Page<MemberMission> memberMissionPage = memberMissionRepository.findByMemberIdAndStatus(memberId, MemberMissionStatus.ONGOING, pageable);
        return MemberMissionConverter.toMemberMissionListResponseDto(memberMissionPage);
    }
}

