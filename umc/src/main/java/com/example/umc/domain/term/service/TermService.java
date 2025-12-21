package com.example.umc.domain.term.service;

import com.example.umc.domain.term.dto.TermListResponseDto;
import com.example.umc.domain.term.dto.TermResponseDto;
import com.example.umc.domain.term.entity.Term;
import com.example.umc.domain.term.repository.TermRepository;
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
public class TermService {

    private final TermRepository termRepository;

    public TermListResponseDto getTerms(Pageable pageable) {
        Page<Term> termPage = termRepository.findTerms(pageable);

        List<TermResponseDto> termDtos = termPage.getContent().stream()
                .map(term -> TermResponseDto.builder()
                        .termId(term.getId())
                        .content(term.getContent())
                        .build())
                .collect(Collectors.toList());

        return TermListResponseDto.builder()
                .terms(termDtos)
                .currentPage(termPage.getNumber())
                .totalPages(termPage.getTotalPages())
                .totalElements(termPage.getTotalElements())
                .build();
    }
}

