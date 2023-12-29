package com.example.airlinemanagementstarter.models;

import jakarta.persistence.*;

import java.util.List;

@Entity(name = "airports")
public class Airport extends BaseModel {
    private String name;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(value = EnumType.ORDINAL)
    private List<SupportedAirplaneTypes> supportedAirplaneTypes;

    @Enumerated(EnumType.ORDINAL)
    private AirportStatus airportStatus;
}
