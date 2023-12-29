package com.example.airlinemanagementsolution.services;

import com.example.airlinemanagementsolution.exceptions.*;
import com.example.airlinemanagementsolution.models.Flight;
import com.example.airlinemanagementsolution.models.SeatType;
import org.springframework.data.util.Pair;

import java.util.Date;
import java.util.List;

public interface FlightService {
    Flight createFlight(int userId, int airplaneId, int airportId, Date startTime, Date endTime,
                               List<Pair<SeatType, Double>> pricingConfig) throws UserNotFoundException, AirplaneNotFoundException, AirportNotFoundException, UnAuthorizedAccessException, InvalidDateException;
}
