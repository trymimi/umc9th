package com.example.umc.domain.mapping.controller;

import com.example.umc.domain.mapping.dto.MemberMissionListResponseDto;
import com.example.umc.domain.mapping.service.MemberMissionService;
import com.example.umc.global.annotation.ValidPage;
import com.example.umc.global.apiPayload.ApiResponse;
import com.example.umc.global.apiPayload.code.GeneralSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "회원 미션", description = "회원 미션 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member-missions")
public class MemberMissionController {

    private final MemberMissionService memberMissionService;

    @Operation(summary = "회원 미션 목록 조회", description = "조건에 맞는 회원 미션 목록을 페이징하여 조회합니다.")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "조회 성공")
    })
    @GetMapping
    public ApiResponse<MemberMissionListResponseDto> getMemberMissions(
            @Parameter(description = "회원 ID") @RequestParam(required = false) Long memberId,
            @Parameter(description = "미션 상태") @RequestParam(required = false) String status,
            @Parameter(description = "페이지 번호 (1 이상)") @ValidPage @RequestParam int page,
            @Parameter(description = "페이지 크기") @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page - 1, size);
        MemberMissionListResponseDto response = memberMissionService.getMemberMissions(memberId, status, pageable);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, response);
    }

    @Operation(summary = "내가 진행중인 미션 목록 조회", description = "특정 회원이 진행중인 미션 목록을 페이징하여 조회합니다.")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "조회 성공")
    })
    @GetMapping("/members/{memberId}/ongoing")
    public ApiResponse<MemberMissionListResponseDto> getMyOngoingMissions(
            @Parameter(description = "회원 ID") @PathVariable Long memberId,
            @Parameter(description = "페이지 번호 (1 이상)") @ValidPage @RequestParam int page,
            @Parameter(description = "페이지 크기") @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page - 1, size);
        MemberMissionListResponseDto response = memberMissionService.getMyOngoingMissions(memberId, pageable);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, response);
    }
}

