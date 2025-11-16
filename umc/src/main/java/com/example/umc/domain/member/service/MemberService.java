package com.example.umc.domain.member.service;

import com.example.umc.domain.member.dto.MemberListResponseDto;
import com.example.umc.domain.member.dto.MemberResponseDto;
import com.example.umc.domain.member.entity.Member;
import com.example.umc.domain.member.repository.MemberRepository;
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
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberListResponseDto getMembers(String name, String status, Pageable pageable) {
        com.example.umc.domain.member.enums.MemberStatus memberStatus = null;
        if (status != null && !status.isEmpty()) {
            try {
                memberStatus = com.example.umc.domain.member.enums.MemberStatus.valueOf(status.toUpperCase());
            } catch (IllegalArgumentException e) {
                memberStatus = null;
            }
        }

        Page<Member> memberPage = memberRepository.findMembersWithFilters(name, memberStatus, pageable);

        List<MemberResponseDto> memberDtos = memberPage.getContent().stream()
                .map(member -> MemberResponseDto.builder()
                        .memberId(member.getId())
                        .name(member.getName())
                        .gender(member.getGender() != null ? member.getGender().name() : null)
                        .status(member.getStatus() != null ? member.getStatus().name() : null)
                        .point(member.getPoint())
                        .build())
                .collect(Collectors.toList());

        return MemberListResponseDto.builder()
                .members(memberDtos)
                .currentPage(memberPage.getNumber())
                .totalPages(memberPage.getTotalPages())
                .totalElements(memberPage.getTotalElements())
                .build();
    }
}

