package com.example.airlinemanagementsolution.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "airports")
public class Airport extends BaseModel {
    private String name;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(value = EnumType.ORDINAL)
    private List<SupportedAirplaneTypes> supportedAirplaneTypes;

    @Enumerated(EnumType.ORDINAL)
    private AirportStatus airportStatus;
}
