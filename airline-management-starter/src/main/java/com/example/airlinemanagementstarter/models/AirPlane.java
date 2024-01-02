package com.example.airlinemanagementstarter.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AirPlane extends BaseModel {
    private String name;
    private String description;
}
