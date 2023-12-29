package com.example.airlinemanagementsolution.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "seats")
public class Seat extends BaseModel{
    private String name;
    private SeatType seatType;
    @ManyToOne
    private AirPlane airPlane;
}
