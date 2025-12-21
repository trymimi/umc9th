package com.example.umc.domain.support.service;

import com.example.umc.domain.support.dto.SupportListResponseDto;
import com.example.umc.domain.support.dto.SupportResponseDto;
import com.example.umc.domain.support.entity.Support;
import com.example.umc.domain.support.repository.SupportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SupportService {

    private final SupportRepository supportRepository;

    public SupportListResponseDto getSupports(Long memberId, String status, Pageable pageable) {
        com.example.umc.domain.support.enums.SupportStatus supportStatus = null;
        if (status != null && !status.isEmpty()) {
            try {
                supportStatus = com.example.umc.domain.support.enums.SupportStatus.valueOf(status.toUpperCase());
            } catch (IllegalArgumentException e) {
                supportStatus = null;
            }
        }

        Page<Support> supportPage = supportRepository.findSupportsWithFilters(memberId, supportStatus, pageable);

        List<SupportResponseDto> supportDtos = supportPage.getContent().stream()
                .map(support -> SupportResponseDto.builder()
                        .supportId(support.getId())
                        .memberId(support.getMember().getId())
                        .subject(support.getSubject())
                        .status(support.getStatus().name())
                        .build())
                .collect(Collectors.toList());

        return SupportListResponseDto.builder()
                .supports(supportDtos)
                .currentPage(supportPage.getNumber())
                .totalPages(supportPage.getTotalPages())
                .totalElements(supportPage.getTotalElements())
                .build();
    }
}

