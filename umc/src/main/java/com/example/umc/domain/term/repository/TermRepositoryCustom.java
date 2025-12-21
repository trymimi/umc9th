package com.example.umc.domain.term.repository;

import com.example.umc.domain.term.entity.Term;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TermRepositoryCustom {
    Page<Term> findTerms(Pageable pageable);
}

