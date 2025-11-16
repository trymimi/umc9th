package com.example.umc.domain.support.repository;

import com.example.umc.domain.support.entity.Support;
import com.example.umc.domain.support.enums.SupportStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

public interface SupportRepository extends JpaRepository<Support, Long> {
    // 내 문의 목록(최신순)
    Page<Support> findByMemberIdOrderByIdDesc(Long memberId, Pageable pageable);

    // 상태 필터 페이징
    Page<Support> findByMemberIdAndStatusOrderByIdDesc(Long memberId, SupportStatus status, Pageable pageable);

    // 상태별 개수
    long countByMemberIdAndStatus(Long memberId, SupportStatus status);

    // =====JPQL=====
    
    // 내 문의 목록(최신순)
    @Query("SELECT s FROM Support s " +
           "WHERE s.member.id = :memberId " +
           "ORDER BY s.id DESC")
    Page<Support> findMySupportsOrdered(@Param("memberId") Long memberId, Pageable pageable);

    // 상태 필터 페이징
    @Query("SELECT s FROM Support s " +
           "WHERE s.member.id = :memberId AND s.status = :status " +
           "ORDER BY s.id DESC")
    Page<Support> findMySupportsByStatus(@Param("memberId") Long memberId,
                                         @Param("status") SupportStatus status,
                                         Pageable pageable);

    // 상태별 개수
    @Query("SELECT COUNT(s) FROM Support s " +
           "WHERE s.member.id = :memberId AND s.status = :status")
    long countMySupportsByStatus(@Param("memberId") Long memberId, 
                                 @Param("status") SupportStatus status);

}
