package com.example.umc.domain.support.repository;

import com.example.umc.domain.support.entity.Support;
import com.example.umc.domain.support.enums.SupportStatus;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.example.umc.domain.support.entity.QSupport.support;

@RequiredArgsConstructor
public class SupportRepositoryCustomImpl implements SupportRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Support> findSupportsWithFilters(Long memberId, SupportStatus status, Pageable pageable) {
        List<Support> supports = queryFactory
                .selectFrom(support)
                .where(
                        memberIdEq(memberId),
                        statusEq(status)
                )
                .orderBy(support.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = queryFactory
                .select(support.count())
                .from(support)
                .where(
                        memberIdEq(memberId),
                        statusEq(status)
                )
                .fetchOne();

        return new PageImpl<>(supports, pageable, total != null ? total : 0);
    }

    private BooleanExpression memberIdEq(Long memberId) {
        return memberId != null ? support.member.id.eq(memberId) : null;
    }

    private BooleanExpression statusEq(SupportStatus status) {
        return status != null ? support.status.eq(status) : null;
    }
}

