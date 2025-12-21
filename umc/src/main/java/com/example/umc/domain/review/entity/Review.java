package com.example.umc.domain.review.entity;

import com.example.umc.domain.member.entity.Member;
import com.example.umc.domain.store.entity.Store;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity @Table(name = "reviews", indexes = {
        @Index(name="ix_review_store", columnList="store_id"),
        @Index(name="ix_review_member", columnList="member_id")
})
@Getter @Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
public class Review {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false,
            foreignKey = @ForeignKey(name="fk_review_store"))
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false,
            foreignKey = @ForeignKey(name="fk_review_member"))
    private Member member;

    @Column(name = "rating")
    private Float rating;

    @Lob
    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "photo", length = 500)
    private String photo;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
}
