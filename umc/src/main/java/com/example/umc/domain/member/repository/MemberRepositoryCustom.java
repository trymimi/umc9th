package com.example.umc.domain.member.repository;

import com.example.umc.domain.member.entity.Member;
import com.example.umc.domain.member.enums.MemberStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MemberRepositoryCustom {
    Page<Member> findMembersWithFilters(String name, MemberStatus status, Pageable pageable);
}

