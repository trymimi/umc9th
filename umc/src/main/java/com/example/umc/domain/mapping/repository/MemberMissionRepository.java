package com.example.umc.domain.mapping.repository;

import com.example.umc.domain.mapping.entity.MemberMission;
import com.example.umc.domain.mapping.enums.MemberMissionStatus;
import com.example.umc.domain.mapping.id.MemberMissionId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberMissionRepository extends JpaRepository<MemberMission, MemberMissionId> {
    // 내가 진행중/완료한 미션 페이징
    Page<MemberMission> findByMemberIdAndStatus(Long memberId, MemberMissionStatus status, Pageable pageable);

    // 카운트(마이페이지 통계)
    long countByMemberIdAndStatus(Long memberId, MemberMissionStatus status);

    // 중복 참여 방지 체크
    boolean existsByMemberIdAndMissionId(Long memberId, Long missionId);
}
