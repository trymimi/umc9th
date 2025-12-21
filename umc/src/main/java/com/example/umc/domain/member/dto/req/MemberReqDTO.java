package com.example.umc.domain.member.dto.req;

import com.example.umc.domain.member.enums.Gender;

import java.time.LocalDate;
import java.util.List;

// 회원가입 요청
public class MemberReqDTO {

    public record JoinDTO(
            String name,
            Gender gender,
            LocalDate birth,
            String address,
            String specAddress,
            List<Long> preferCategory
    ){}
}

