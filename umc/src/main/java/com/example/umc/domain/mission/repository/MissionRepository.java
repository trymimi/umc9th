package com.example.umc.domain.mission.repository;

import com.example.umc.domain.mission.entity.Mission;
import com.example.umc.domain.mission.enums.MissionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MissionRepository extends JpaRepository<Mission, Long> {
    // 현재 선택 된 지역에서 도전 가능한 미션 목록. 페이징 포함
    Page<Mission> findByStoreRegionCodeAndStatus(String regionCode, MissionStatus status, Pageable pageable);

    Page<Mission> findAvailableForMemberInRegion(@Param("memberId") Long memberId,
                                                 @Param("regionCode") String regionCode,
                                                 Pageable pageable);
}
