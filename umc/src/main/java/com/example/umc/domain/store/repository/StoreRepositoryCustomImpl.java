package com.example.umc.domain.store.repository;

import com.example.umc.domain.store.entity.Store;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.example.umc.domain.store.entity.QStore.store;

@RequiredArgsConstructor
public class StoreRepositoryCustomImpl implements StoreRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Store> findStoresWithFilters(String storeName, Pageable pageable) {
        List<Store> stores = queryFactory
                .selectFrom(store)
                .where(storeNameContains(storeName))
                .orderBy(store.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = queryFactory
                .select(store.count())
                .from(store)
                .where(storeNameContains(storeName))
                .fetchOne();

        return new PageImpl<>(stores, pageable, total != null ? total : 0);
    }

    private BooleanExpression storeNameContains(String storeName) {
        return storeName != null && !storeName.isEmpty() ? store.name.contains(storeName) : null;
    }
}

