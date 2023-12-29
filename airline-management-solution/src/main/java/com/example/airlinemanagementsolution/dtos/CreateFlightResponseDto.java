package com.example.airlinemanagementsolution.dtos;

import com.example.airlinemanagementsolution.models.Flight;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateFlightResponseDto {
    private Flight flight;
    private ResponseStatus responseStatus;
}
