package com.travelkit.backend.domain;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Weather {

    @Id
    @GeneratedValue
    @Column(name = "weather_id")
    Long id;

    private LocalDate localDate;

    private int temp;

    private String mainWeather;

    @JsonBackReference
    @ManyToOne
    private Checklist checklist;
}
