package com.example.airlinemanagementstarter.dtos;

import com.example.airlinemanagementstarter.models.Flight;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateFlightResponseDto {
    private Flight flight;
    private ResponseStatus responseStatus;
}
