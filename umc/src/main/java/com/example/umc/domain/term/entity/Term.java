package com.example.umc.domain.term.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name = "term")
@Getter @Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Term {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "term_id")
    private Long id;

    @Lob
    @Column(name = "content", nullable = false)
    private String content;
}
