package com.example.airlinemanagementstarter.models;

import jakarta.persistence.Entity;

@Entity(name = "seats")
public class Seat extends BaseModel{
    private String name;

}
