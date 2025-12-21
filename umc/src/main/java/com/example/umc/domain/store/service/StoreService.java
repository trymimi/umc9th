package com.example.umc.domain.store.service;

import com.example.umc.domain.store.dto.StoreListResponseDto;
import com.example.umc.domain.store.dto.StoreResponseDto;
import com.example.umc.domain.store.entity.Store;
import com.example.umc.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreService {

    private final StoreRepository storeRepository;

    public StoreListResponseDto getStores(String storeName, Pageable pageable) {
        Page<Store> storePage = storeRepository.findStoresWithFilters(storeName, pageable);

        List<StoreResponseDto> storeDtos = storePage.getContent().stream()
                .map(store -> StoreResponseDto.builder()
                        .storeId(store.getId())
                        .name(store.getName())
                        .description(store.getDescription())
                        .location(store.getLocation())
                        .build())
                .collect(Collectors.toList());

        return StoreListResponseDto.builder()
                .stores(storeDtos)
                .currentPage(storePage.getNumber())
                .totalPages(storePage.getTotalPages())
                .totalElements(storePage.getTotalElements())
                .build();
    }
}

