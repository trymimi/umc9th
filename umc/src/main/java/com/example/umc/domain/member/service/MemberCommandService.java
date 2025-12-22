package com.example.umc.domain.member.service;

import com.example.umc.domain.member.dto.req.MemberReqDTO;
import com.example.umc.domain.member.dto.res.MemberResDTO;

public interface MemberCommandService {
    MemberResDTO.JoinDTO signup(MemberReqDTO.JoinDTO dto);
}

