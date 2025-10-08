package com.example.umc.domain.term.entity;

import jakarta.persistence.Embeddable;
import lombok.*;
import java.io.Serializable;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Embeddable
public class UserTermId implements Serializable {
    private Long userId;
    private Long termId;
}
