package com.example.umc.domain.support.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SupportResponseDto {
    private Long supportId;
    private Long memberId;
    private String subject;
    private String status;
}

