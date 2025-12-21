package com.example.umc.domain.store.repository;

import com.example.umc.domain.store.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StoreRepositoryCustom {
    Page<Store> findStoresWithFilters(String storeName, Pageable pageable);
}

