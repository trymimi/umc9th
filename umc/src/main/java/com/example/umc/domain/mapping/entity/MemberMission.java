package com.example.umc.domain.mapping.entity;

import com.example.umc.domain.mapping.enums.MemberMissionStatus;
import com.example.umc.domain.mapping.id.MemberMissionId;
import com.example.umc.domain.member.entity.Member;
import com.example.umc.domain.mission.entity.Mission;
import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name = "member_mission")
@Getter @Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberMission {

    @EmbeddedId
    private MemberMissionId id;

    @MapsId("memberId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_um_member"))
    private Member member;

    @MapsId("missionId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_um_mission"))
    private Mission mission;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 30, nullable = false)
    private MemberMissionStatus status; // IN_PROGRESS/COMPLETED 등
}