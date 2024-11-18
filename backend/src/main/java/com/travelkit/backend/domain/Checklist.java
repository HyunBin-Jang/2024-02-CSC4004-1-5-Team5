package com.travelkit.backend.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
public class Checklist {
    @Id
    @GeneratedValue
    @Column(name = "checklist_id")
    Long id;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Embedded
    private Destination destination;

    private LocalDate departureDate;

    private LocalDate arrivalDate;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "checklist")
    private List<Item> checklistItems;
}