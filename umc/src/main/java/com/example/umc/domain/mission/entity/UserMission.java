package com.example.umc.domain.mission.entity;

import com.example.umc.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name = "user_mission")
@Getter @Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserMission {

    @EmbeddedId
    private UserMissionId id;

    @MapsId("userId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_um_user"))
    private Member user;

    @MapsId("missionId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_um_mission"))
    private Mission mission;

    @Column(name = "status", length = 30)
    private String status; // IN_PROGRESS/COMPLETED 등
}