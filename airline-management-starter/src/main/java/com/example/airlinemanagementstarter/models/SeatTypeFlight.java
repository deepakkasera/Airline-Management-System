package com.example.airlinemanagementstarter.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SeatTypeFlight extends BaseModel {
    @ManyToOne
    private Flight flight;

    @Enumerated(value = EnumType.ORDINAL)
    private SeatType seatType;

    private double price;
}