package com.example.umc.domain.mapping.repository;

import com.example.umc.domain.mapping.entity.MemberTerm;
import com.example.umc.domain.mapping.id.MemberTermId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberTermRepository extends JpaRepository<MemberTerm, MemberTermId> {
    // 특정 약관에 동의했는지
    boolean existsByMemberIdAndTermId(Long memberId, Long termId);

    // 약관 동의 수
    long countByTermId(Long termId);
}
