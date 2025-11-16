package com.example.umc.domain.review.repository;

import com.example.umc.domain.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewRepositoryCustom {
    // 마이페이지: 내 리뷰 페이징(최신순)
    Page<Review> findByMemberIdOrderByIdDesc(Long memberId, Pageable pageable);

    // 마이페이지: 내 리뷰 개수
    long countByMemberId(Long memberId);

    // 리뷰 중복 방지
    boolean existsByMemberIdAndStoreId(Long memberId, Long storeId);

    // =====JPQL=====
    
    // 내 리뷰 페이징(최신순)
    @Query("SELECT r FROM Review r " +
           "WHERE r.member.id = :memberId " +
           "ORDER BY r.id DESC")
    Page<Review> findMyReviewsOrdered(@Param("memberId") Long memberId, Pageable pageable);

    // 내 리뷰 개수
    @Query("SELECT COUNT(r) FROM Review r WHERE r.member.id = :memberId")
    long countMyReviews(@Param("memberId") Long memberId);

    // 리뷰 중복 방지 체크
    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END " +
           "FROM Review r " +
           "WHERE r.member.id = :memberId AND r.store.id = :storeId")
    boolean checkReviewExists(@Param("memberId") Long memberId, 
                              @Param("storeId") Long storeId);
}
