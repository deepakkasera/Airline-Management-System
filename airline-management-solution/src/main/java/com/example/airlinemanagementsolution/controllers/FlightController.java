package com.example.airlinemanagementsolution.controllers;

import com.example.airlinemanagementsolution.dtos.CreateFlightRequestDto;
import com.example.airlinemanagementsolution.dtos.CreateFlightResponseDto;
import com.example.airlinemanagementsolution.dtos.ResponseStatus;
import com.example.airlinemanagementsolution.models.Flight;
import com.example.airlinemanagementsolution.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class FlightController {
    private FlightService flightService;

    @Autowired
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    public CreateFlightResponseDto createFlight(CreateFlightRequestDto requestDto) {
        CreateFlightResponseDto responseDto = new CreateFlightResponseDto();

        try {
            Flight flight = flightService.createFlight(requestDto.getUserId(), requestDto.getAirplaneId(),
                    requestDto.getAirportId(), requestDto.getStartTime(), requestDto.getEndTime(), requestDto.getPricingConfig());
            responseDto.setFlight(flight);
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
        }

        return responseDto;
    }
}
