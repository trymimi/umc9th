package com.example.umc.domain.term.repository;

import com.example.umc.domain.term.entity.Term;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.example.umc.domain.term.entity.QTerm.term;

@RequiredArgsConstructor
public class TermRepositoryCustomImpl implements TermRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Term> findTerms(Pageable pageable) {
        List<Term> terms = queryFactory
                .selectFrom(term)
                .orderBy(term.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = queryFactory
                .select(term.count())
                .from(term)
                .fetchOne();

        return new PageImpl<>(terms, pageable, total != null ? total : 0);
    }
}

