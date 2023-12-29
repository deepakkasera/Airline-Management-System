package com.example.airlinemanagementsolution.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity(name = "tickets")
public class Ticket extends BaseModel {
    @ManyToOne
    private Flight flight;
    private Date timeOfBooking;
    @ManyToOne
    private User user;
    private TicketStatus ticketStatus;
}
