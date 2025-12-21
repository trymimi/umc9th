package com.example.umc.domain.member.repository;

import com.example.umc.domain.member.entity.Member;
import com.example.umc.domain.member.enums.MemberStatus;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.example.umc.domain.member.entity.QMember.member;

@RequiredArgsConstructor
public class MemberRepositoryCustomImpl implements MemberRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Member> findMembersWithFilters(String name, MemberStatus status, Pageable pageable) {
        List<Member> members = queryFactory
                .selectFrom(member)
                .where(
                        nameContains(name),
                        statusEq(status)
                )
                .orderBy(member.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = queryFactory
                .select(member.count())
                .from(member)
                .where(
                        nameContains(name),
                        statusEq(status)
                )
                .fetchOne();

        return new PageImpl<>(members, pageable, total != null ? total : 0);
    }

    private BooleanExpression nameContains(String name) {
        return name != null && !name.isEmpty() ? member.name.contains(name) : null;
    }

    private BooleanExpression statusEq(MemberStatus status) {
        return status != null ? member.status.eq(status) : null;
    }
}

