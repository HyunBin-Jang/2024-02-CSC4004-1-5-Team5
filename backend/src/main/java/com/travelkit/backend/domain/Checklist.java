package com.travelkit.backend.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Checklist {
    @Id
    @GeneratedValue
    @Column(name = "checklist_id")
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Embedded
    private Destination destination;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "checklist")
    private List<Item> checklistItems;
}
