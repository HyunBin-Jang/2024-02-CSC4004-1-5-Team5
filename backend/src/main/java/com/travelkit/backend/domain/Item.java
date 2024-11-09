package com.travelkit.backend.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Item {
    @Id
    @GeneratedValue
    private Long id;

    @JoinColumn(nullable = false)
    private String name;

    @JoinColumn(nullable = false)
    private Boolean ischecked;

    @JsonBackReference
    @ManyToOne
    private Checklist checklist;
}
