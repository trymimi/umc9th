package com.example.umc.domain.mapping.controller;

import com.example.umc.domain.mapping.dto.MemberMissionListResponseDto;
import com.example.umc.domain.mapping.service.MemberMissionService;
import com.example.umc.global.apiPayload.ApiResponse;
import com.example.umc.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member-missions")
public class MemberMissionController {

    private final MemberMissionService memberMissionService;

    @GetMapping
    public ApiResponse<MemberMissionListResponseDto> getMemberMissions(
            @RequestParam(required = false) Long memberId,
            @RequestParam(required = false) String status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        MemberMissionListResponseDto response = memberMissionService.getMemberMissions(memberId, status, pageable);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, response);
    }
}

