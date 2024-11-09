package com.travelkit.backend.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {

    @Id
    @Column(name = "member_id")
    private String id;

    private String name;

    private String password;

    private String email;

    @JsonManagedReference
    @OneToMany(mappedBy = "member")
    private List<Checklist> checklists = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "member")
    private List<Post> posts = new ArrayList<>();
}
