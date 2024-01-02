package com.example.airlinemanagementstarter.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Flight extends BaseModel {
    @ManyToOne
    private AirPlane airPlane;
    private Date startTime;
    private Date endTime;
    @ManyToOne
    private Airport airport;
}
