package com.example.airlinemanagementstarter.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity(name = "flights")
public class Flight extends BaseModel {
    @ManyToOne
    private AirPlane airPlane;
    private Date startTime;
    private Date endTime;
    @ManyToOne
    private Airport airport;
}
