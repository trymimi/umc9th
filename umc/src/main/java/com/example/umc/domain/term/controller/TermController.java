package com.example.umc.domain.term.controller;

import com.example.umc.domain.term.dto.TermListResponseDto;
import com.example.umc.domain.term.service.TermService;
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
@RequestMapping("/api/terms")
public class TermController {

    private final TermService termService;

    @GetMapping
    public ApiResponse<TermListResponseDto> getTerms(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        TermListResponseDto response = termService.getTerms(pageable);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, response);
    }
}

