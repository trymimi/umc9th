package com.example.umc.domain.member.dto.res;

import lombok.Builder;

import java.time.LocalDateTime;

// 회원가입 응답
public class MemberResDTO {

    @Builder
    public record JoinDTO(
            Long memberId,
            LocalDateTime createAt
    ){}
}

