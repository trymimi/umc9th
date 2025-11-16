package com.example.umc.domain.mapping.entity;

import com.example.umc.domain.mapping.id.MemberTermId;
import com.example.umc.domain.member.entity.Member;
import com.example.umc.domain.term.entity.Term;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity @Table(name = "member_term")
@Getter @Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberTerm {

    @EmbeddedId
    private MemberTermId id;

    @MapsId("memberId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_ut_member"))
    private Member member;

    @MapsId("termId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "term_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_ut_term"))
    private Term term;

    @Column(name = "agreed_at", nullable = false)
    private LocalDateTime agreedAt = LocalDateTime.now();
}
