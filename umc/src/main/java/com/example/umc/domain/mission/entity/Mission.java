package com.example.umc.domain.mission.entity;

import com.example.umc.domain.mapping.entity.MemberMission;
import com.example.umc.domain.mission.enums.MissionStatus;
import com.example.umc.domain.store.entity.Store;
import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity @Table(name = "missions", indexes = {
        @Index(name="ix_mission_status", columnList="status"),
        @Index(name="ix_mission_store", columnList="store_id")
})
@Getter @Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Mission {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_mission_store"))
    private Store store;

    @Column(name = "description", length = 300)
    private String description;

    @Column(name = "point")
    private Integer point;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 20, nullable = false)
    private MissionStatus status;

    @OneToMany(mappedBy = "mission", fetch = FetchType.LAZY)
    private List<MemberMission> memberMissions = new ArrayList<>();
}
