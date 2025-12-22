package com.example.umc.domain.member.controller;

import com.example.umc.domain.member.dto.req.MemberReqDTO;
import com.example.umc.domain.member.dto.res.MemberListResponseDto;
import com.example.umc.domain.member.dto.res.MemberResDTO;
import com.example.umc.domain.member.service.MemberCommandService;
import com.example.umc.domain.member.service.MemberService;
import com.example.umc.global.apiPayload.ApiResponse;
import com.example.umc.global.apiPayload.code.GeneralSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;
    private final MemberCommandService memberCommandService;

    @GetMapping
    public ApiResponse<MemberListResponseDto> getMembers(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        MemberListResponseDto response = memberService.getMembers(name, status, pageable);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, response);
    }

    @PostMapping("/sign-up")
    public ApiResponse<MemberResDTO.JoinDTO> signup(
            @Valid @RequestBody MemberReqDTO.JoinDTO dto
    ) {
        MemberResDTO.JoinDTO response = memberCommandService.signup(dto);
        return ApiResponse.onSuccess(GeneralSuccessCode.CREATED, response);
    }
}

