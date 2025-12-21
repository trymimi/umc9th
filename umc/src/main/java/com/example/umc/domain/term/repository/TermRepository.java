package com.example.umc.domain.term.repository;

import com.example.umc.domain.term.entity.Term;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TermRepository extends JpaRepository<Term, Long>, TermRepositoryCustom {
}

