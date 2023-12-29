package com.example.airlinemanagementsolution.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "airplanes")
public class AirPlane extends BaseModel {
    private String name;
    private String description;
    @OneToMany(mappedBy = "airPlane")
    private List<Seat> seats;
}
