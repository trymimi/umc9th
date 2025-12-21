package com.example.umc.domain.mapping.repository;

import com.example.umc.domain.mapping.entity.MemberMission;
import com.example.umc.domain.mapping.enums.MemberMissionStatus;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.example.umc.domain.mapping.entity.QMemberMission.memberMission;

@RequiredArgsConstructor
public class MemberMissionRepositoryCustomImpl implements MemberMissionRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<MemberMission> findMemberMissionsWithFilters(Long memberId, MemberMissionStatus status, Pageable pageable) {
        List<MemberMission> memberMissions = queryFactory
                .selectFrom(memberMission)
                .where(
                        memberIdEq(memberId),
                        statusEq(status)
                )
                .orderBy(memberMission.member.id.desc(), memberMission.mission.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = queryFactory
                .select(memberMission.count())
                .from(memberMission)
                .where(
                        memberIdEq(memberId),
                        statusEq(status)
                )
                .fetchOne();

        return new PageImpl<>(memberMissions, pageable, total != null ? total : 0);
    }

    private BooleanExpression memberIdEq(Long memberId) {
        return memberId != null ? memberMission.member.id.eq(memberId) : null;
    }

    private BooleanExpression statusEq(MemberMissionStatus status) {
        return status != null ? memberMission.status.eq(status) : null;
    }
}

