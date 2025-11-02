package com.example.umc.domain.member.entity;

import com.example.umc.domain.member.enums.Gender;
import com.example.umc.domain.member.enums.MemberStatus;
import com.example.umc.domain.mapping.entity.MemberMission;
import com.example.umc.domain.review.entity.Review;
import com.example.umc.domain.support.entity.Support;
import com.example.umc.domain.mapping.entity.MemberTerm;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity @Table(name = "members")
@Getter @Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access =  AccessLevel.PRIVATE)
public class Member {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "birthday")
    private LocalDate birthday;

    @Column(name = "address", length = 255)
    private String address;

    @Column(name = "favorite_food", length = 100)
    private String favoriteFood;

    @Column(name = "point")
    private Long point;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 20)
    private MemberStatus status;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<MemberMission> memberMissions = new ArrayList<>();

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<MemberTerm> memberTerms = new ArrayList<>();

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<Support> supports = new ArrayList<>();
}
