package com.example.umc.domain.store.entity;

import com.example.umc.domain.mission.entity.Mission;
import com.example.umc.domain.review.entity.Review;
import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity @Table(name = "stores")
@Getter @Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Store {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Long id;

    @Column(name = "name", nullable = false, length = 150)
    private String name;

    @Column(name = "description", length = 300)
    private String description;

    @Column(name = "location", length = 200)
    private String location;

    @OneToMany(mappedBy = "store", fetch = FetchType.LAZY)
    private List<Mission> missions = new ArrayList<>();

    @OneToMany(mappedBy = "store", fetch = FetchType.LAZY)
    private List<Review> reviews = new ArrayList<>();
}
