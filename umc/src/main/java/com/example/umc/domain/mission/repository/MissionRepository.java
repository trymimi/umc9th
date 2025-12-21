package com.example.umc.domain.mission.repository;

import com.example.umc.domain.mission.entity.Mission;
import com.example.umc.domain.mission.enums.MissionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MissionRepository extends JpaRepository<Mission, Long>, MissionRepositoryCustom {
    // 현재 선택 된 지역에서 도전 가능한 미션 목록. 페이징 포함
    Page<Mission> findByStoreRegionCodeAndStatus(String regionCode, MissionStatus status, Pageable pageable);

    // =====JPQL=====
    
    // 상태로 미션 조회
    @Query("SELECT m FROM Mission m WHERE m.status = :status")
    List<Mission> findByStatus(@Param("status") MissionStatus status);

    // 특정 스토어의 미션 조회
    @Query("SELECT m FROM Mission m WHERE m.store.id = :storeId")
    List<Mission> findByStoreId(@Param("storeId") Long storeId);

    // 특정 스토어의 미션 조회 (페이징)
    @Query("SELECT m FROM Mission m WHERE m.store.id = :storeId ORDER BY m.id DESC")
    Page<Mission> findByStoreIdWithPaging(@Param("storeId") Long storeId, Pageable pageable);

    // 상태와 스토어로 미션 조회 (페이징)
    @Query("SELECT m FROM Mission m WHERE m.status = :status AND m.store.id = :storeId")
    Page<Mission> findByStatusAndStoreId(@Param("status") MissionStatus status,
                                         @Param("storeId") Long storeId,
                                         Pageable pageable);
}
