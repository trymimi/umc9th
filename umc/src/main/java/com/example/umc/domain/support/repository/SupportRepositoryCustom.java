package com.example.umc.domain.support.repository;

import com.example.umc.domain.support.entity.Support;
import com.example.umc.domain.support.enums.SupportStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SupportRepositoryCustom {
    Page<Support> findSupportsWithFilters(Long memberId, SupportStatus status, Pageable pageable);
}

