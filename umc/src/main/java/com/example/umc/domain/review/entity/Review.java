package com.example.umc.domain.review.entity;

import com.example.umc.domain.member.entity.Member;
import com.example.umc.domain.store.entity.Store;
import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name = "reviews", indexes = {
        @Index(name="ix_review_store", columnList="store_id"),
        @Index(name="ix_review_user", columnList="user_id")
})
@Getter @Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Review {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false,
            foreignKey = @ForeignKey(name="fk_review_store"))
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false,
            foreignKey = @ForeignKey(name="fk_review_user"))
    private Member user;

    @Column(name = "rating")
    private Integer rating;

    @Lob
    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "photo", length = 500)
    private String photo;
}
