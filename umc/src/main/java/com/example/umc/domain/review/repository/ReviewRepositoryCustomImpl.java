package com.example.umc.domain.review.repository;

import com.example.umc.domain.review.entity.Review;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.example.umc.domain.review.entity.QReview.review;
import static com.example.umc.domain.store.entity.QStore.store;

@RequiredArgsConstructor
public class ReviewRepositoryCustomImpl implements ReviewRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Review> findReviewsWithFilters(Long storeId, String storeName, Integer minRating, Integer maxRating, Pageable pageable) {
        List<Review> reviews = queryFactory
                .selectFrom(review)
                .join(review.store, store)
                .where(
                        storeIdEq(storeId),
                        storeNameContains(storeName),
                        ratingBetween(minRating, maxRating)
                )
                .orderBy(review.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = queryFactory
                .select(review.count())
                .from(review)
                .join(review.store, store)
                .where(
                        storeIdEq(storeId),
                        storeNameContains(storeName),
                        ratingBetween(minRating, maxRating)
                )
                .fetchOne();

        return new PageImpl<>(reviews, pageable, total != null ? total : 0);
    }

    private BooleanExpression storeIdEq(Long storeId) {
        return storeId != null ? review.store.id.eq(storeId) : null;
    }

    private BooleanExpression storeNameContains(String storeName) {
        return storeName != null && !storeName.isEmpty() ? store.name.contains(storeName) : null;
    }

    private BooleanExpression ratingBetween(Integer minRating, Integer maxRating) {
        if (minRating != null && maxRating != null) {
            return review.rating.goe(minRating.floatValue())
                    .and(review.rating.lt(maxRating.floatValue()));
        } else if (minRating != null) {
            return review.rating.goe(minRating.floatValue());
        } else if (maxRating != null) {
            return review.rating.lt(maxRating.floatValue());
        }
        return null;
    }
}

