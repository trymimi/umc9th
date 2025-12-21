package com.example.umc.domain.mission.repository;

import com.example.umc.domain.mission.entity.Mission;
import com.example.umc.domain.mission.enums.MissionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MissionRepositoryCustom {
    Page<Mission> findMissionsWithFilters(Long storeId, MissionStatus status, Pageable pageable);
}

