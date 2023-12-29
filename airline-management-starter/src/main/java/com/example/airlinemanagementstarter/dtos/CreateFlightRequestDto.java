package com.example.airlinemanagementstarter.dtos;

import com.example.airlinemanagementstarter.models.SeatType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.util.Pair;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class CreateFlightRequestDto {
    private int userId;
    private int airplaneId;
    private int airportId;
    private Date startTime;
    private Date endTime;
    private List<Pair<SeatType, Double>> pricingConfig;
}
