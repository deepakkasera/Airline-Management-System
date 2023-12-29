package com.example.airlinemanagementstarter.services;

import com.example.airlinemanagementstarter.exceptions.AirplaneNotFoundException;
import com.example.airlinemanagementstarter.exceptions.AirportNotFoundException;
import com.example.airlinemanagementstarter.exceptions.UserNotFoundException;
import com.example.airlinemanagementstarter.models.Flight;
import com.example.airlinemanagementstarter.models.SeatType;
import org.springframework.data.util.Pair;

import java.util.Date;
import java.util.List;

public interface FlightService {
    Flight createFlight(int userId, int airplaneId, int airportId, Date startTime, Date endTime,
                               List<Pair<SeatType, Double>> pricingConfig) throws UserNotFoundException, AirplaneNotFoundException, AirportNotFoundException;
}
