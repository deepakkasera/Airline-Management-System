package com.example.airlinemanagementsolution.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "flight_seats")
public class FlightSeat extends BaseModel {
    @ManyToOne
    private Flight flight;
    @ManyToOne
    private Seat seat;
    private SeatStatus seatStatus;
}
