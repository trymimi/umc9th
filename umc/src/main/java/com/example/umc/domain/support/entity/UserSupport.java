package com.example.umc.domain.support.entity;

import com.example.umc.domain.member.entity.Member;
import com.example.umc.domain.support.enums.SupportStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name = "user_support", indexes = {
        @Index(name="ix_support_user", columnList="user_id"),
        @Index(name="ix_support_status", columnList="status")
})
@Getter @Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserSupport {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false,
            foreignKey = @ForeignKey(name="fk_support_user"))
    private Member user;

    @Column(name = "subject", nullable = false, length = 200)
    private String subject;

    @Column(name = "status", length = 20)
    private SupportStatus status;
}
