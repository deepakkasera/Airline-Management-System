package com.example.airlinemanagementsolution.services;

import com.example.airlinemanagementsolution.exceptions.*;
import com.example.airlinemanagementsolution.models.*;
import com.example.airlinemanagementsolution.repositories.*;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FlightServiceImpl implements FlightService {
    private AirplaneRepository airplaneRepository;
    private AirportRepository airportRepository;
    private FlightRepository flightRepository;
    private UserRepository userRepository;
    private FlightSeatRepository flightSeatRepository;
    private SeatTypeFlightRepository seatTypeFlightRepository;
    private SeatRepository seatRepository;

    public FlightServiceImpl(AirplaneRepository airplaneRepository, AirportRepository airportRepository,
                             FlightRepository flightRepository, UserRepository userRepository,
                             FlightSeatRepository flightSeatRepository, SeatTypeFlightRepository seatTypeFlightRepository,
                             SeatRepository seatRepository) {
        this.airplaneRepository = airplaneRepository;
        this.airportRepository = airportRepository;
        this.flightRepository = flightRepository;
        this.userRepository = userRepository;
        this.flightSeatRepository = flightSeatRepository;
        this.seatTypeFlightRepository = seatTypeFlightRepository;
        this.seatRepository = seatRepository;
    }
    @Override
    public Flight createFlight(int userId, int airplaneId, int airportId, Date startTime, Date endTime, List<Pair<SeatType, Double>> pricingConfig) throws UserNotFoundException, AirplaneNotFoundException, AirportNotFoundException, UnAuthorizedAccessException, InvalidDateException {
        User admin = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
        if (!admin.getUserType().equals(UserType.ADMIN)) {
            throw new UnAuthorizedAccessException("User not authorized to create the flight");
        }

        AirPlane airPlane = airplaneRepository.findById(airplaneId).orElseThrow(() -> new AirplaneNotFoundException("Airplane not found"));
        Airport airport = airportRepository.findById(airportId).orElseThrow(() -> new AirportNotFoundException("Airport not found"));

        Date now = new Date();
        if (startTime.before(now)) {
            throw new InvalidDateException("Start time cannot be before current time");
        }
        if(endTime.before(startTime)){
            throw new InvalidDateException("End time cannot be before start time");
        }

        //Create Flight
        Flight flight = new Flight();
        flight.setAirPlane(airPlane);
        flight.setAirport(airport);
        flight.setStartTime(startTime);
        flight.setEndTime(endTime);
        flight = flightRepository.save(flight);

        List<Seat> seats = seatRepository.findAllByAirPlane_Id(airplaneId);
        List<FlightSeat> flightSeats = new ArrayList<>();
        for (Seat seat : seats) {
            FlightSeat flightSeat = new FlightSeat();
            flightSeat.setFlight(flight);
            flightSeat.setSeatStatus(SeatStatus.AVAILABLE);
            flightSeat.setSeat(seat);
            flightSeats.add(flightSeat);
        }
        flightSeatRepository.saveAll(flightSeats);

        for (Pair<SeatType, Double> priceConfig : pricingConfig) {
            SeatType seatType = priceConfig.getFirst();
            Double price = priceConfig.getSecond();
            SeatTypeFlight seatTypeFlight = new SeatTypeFlight();
            seatTypeFlight.setFlight(flight);
            seatTypeFlight.setPrice(price);
            seatTypeFlight.setSeatType(seatType);
            seatTypeFlightRepository.save(seatTypeFlight);
        }

        return flight;
    }
}
