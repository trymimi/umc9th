package com.example.umc.domain.support.controller;

import com.example.umc.domain.support.dto.SupportListResponseDto;
import com.example.umc.domain.support.service.SupportService;
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
@RequestMapping("/api/supports")
public class SupportController {

    private final SupportService supportService;

    @GetMapping
    public ApiResponse<SupportListResponseDto> getSupports(
            @RequestParam(required = false) Long memberId,
            @RequestParam(required = false) String status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        SupportListResponseDto response = supportService.getSupports(memberId, status, pageable);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, response);
    }
}

