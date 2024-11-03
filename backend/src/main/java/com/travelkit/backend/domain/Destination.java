package com.travelkit.backend.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class Destination {
    private String country;
    private String city;
    protected Destination(){}
    public Destination(String country, String city){
        this.country = country;
        this.city = city;
    }
}
