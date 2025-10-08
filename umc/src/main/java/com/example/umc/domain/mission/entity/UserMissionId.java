package com.example.umc.domain.mission.entity;

import jakarta.persistence.Embeddable;
import lombok.*;
import java.io.Serializable;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Embeddable
public class UserMissionId implements Serializable {
    private Long userId;
    private Long missionId;
}
