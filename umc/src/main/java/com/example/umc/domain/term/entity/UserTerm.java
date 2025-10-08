package com.example.umc.domain.term.entity;

import com.example.umc.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity @Table(name = "user_term")
@Getter @Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserTerm {

    @EmbeddedId
    private UserTermId id;

    @MapsId("userId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_ut_user"))
    private Member user;

    @MapsId("termId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "term_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_ut_term"))
    private Term term;

    @Column(name = "agreed_at", nullable = false)
    private LocalDateTime agreedAt = LocalDateTime.now();
}
