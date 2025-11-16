package com.example.umc.domain.mapping.repository;

import com.example.umc.domain.mapping.entity.MemberMission;
import com.example.umc.domain.mapping.enums.MemberMissionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MemberMissionRepositoryCustom {
    Page<MemberMission> findMemberMissionsWithFilters(Long memberId, MemberMissionStatus status, Pageable pageable);
}

