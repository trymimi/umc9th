package com.example.umc.domain.store.controller;

import com.example.umc.domain.store.dto.StoreListResponseDto;
import com.example.umc.domain.store.service.StoreService;
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
@RequestMapping("/api/stores")
public class StoreController {

    private final StoreService storeService;

    @GetMapping
    public ApiResponse<StoreListResponseDto> getStores(
            @RequestParam(required = false) String storeName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        StoreListResponseDto response = storeService.getStores(storeName, pageable);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, response);
    }
}

