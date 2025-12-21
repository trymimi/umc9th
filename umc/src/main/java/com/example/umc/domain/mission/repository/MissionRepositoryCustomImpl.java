package com.example.umc.domain.mission.repository;

import com.example.umc.domain.mission.entity.Mission;
import com.example.umc.domain.mission.enums.MissionStatus;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.example.umc.domain.mission.entity.QMission.mission;

@RequiredArgsConstructor
public class MissionRepositoryCustomImpl implements MissionRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Mission> findMissionsWithFilters(Long storeId, MissionStatus status, Pageable pageable) {
        List<Mission> missions = queryFactory
                .selectFrom(mission)
                .where(
                        storeIdEq(storeId),
                        statusEq(status)
                )
                .orderBy(mission.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = queryFactory
                .select(mission.count())
                .from(mission)
                .where(
                        storeIdEq(storeId),
                        statusEq(status)
                )
                .fetchOne();

        return new PageImpl<>(missions, pageable, total != null ? total : 0);
    }

    private BooleanExpression storeIdEq(Long storeId) {
        return storeId != null ? mission.store.id.eq(storeId) : null;
    }

    private BooleanExpression statusEq(MissionStatus status) {
        return status != null ? mission.status.eq(status) : null;
    }
}

