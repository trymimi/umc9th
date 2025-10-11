package com.example.umc.domain.term.entity;

import jakarta.persistence.Embeddable;
import lombok.*;
import java.io.Serializable;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode
@Embeddable
public class MemberTermId implements Serializable {
    private Long memberId;
    private Long termId;
}
