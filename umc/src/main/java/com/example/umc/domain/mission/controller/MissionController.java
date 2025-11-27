package com.example.umc.domain.mission.controller;

import com.example.umc.domain.mission.dto.MissionListResponseDto;
import com.example.umc.domain.mission.service.MissionService;
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

@Tag(name = "미션", description = "미션 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/missions")
public class MissionController {

    private final MissionService missionService;

    @Operation(summary = "미션 목록 조회", description = "조건에 맞는 미션 목록을 페이징하여 조회합니다.")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "조회 성공")
    })
    @GetMapping
    public ApiResponse<MissionListResponseDto> getMissions(
            @Parameter(description = "가게 ID") @RequestParam(required = false) Long storeId,
            @Parameter(description = "미션 상태") @RequestParam(required = false) String status,
            @Parameter(description = "페이지 번호 (1 이상)") @ValidPage @RequestParam int page,
            @Parameter(description = "페이지 크기") @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page - 1, size);
        MissionListResponseDto response = missionService.getMissions(storeId, status, pageable);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, response);
    }

    @Operation(summary = "특정 가게의 미션 목록 조회", description = "특정 가게의 미션 목록을 페이징하여 조회합니다.")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "조회 성공")
    })
    @GetMapping("/stores/{storeId}")
    public ApiResponse<MissionListResponseDto> getStoreMissions(
            @Parameter(description = "가게 ID") @PathVariable Long storeId,
            @Parameter(description = "페이지 번호 (1 이상)") @ValidPage @RequestParam int page,
            @Parameter(description = "페이지 크기") @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page - 1, size);
        MissionListResponseDto response = missionService.getStoreMissions(storeId, pageable);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, response);
    }
}

