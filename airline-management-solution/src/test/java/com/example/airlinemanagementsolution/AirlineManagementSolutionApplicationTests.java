package com.example.airlinemanagementsolution;

import com.example.airlinemanagementsolution.controllers.FlightController;
import com.example.airlinemanagementsolution.dtos.CreateFlightRequestDto;
import com.example.airlinemanagementsolution.dtos.CreateFlightResponseDto;
import com.example.airlinemanagementsolution.dtos.ResponseStatus;
import com.example.airlinemanagementsolution.exceptions.UnAuthorizedAccessException;
import com.example.airlinemanagementsolution.models.*;
import com.example.airlinemanagementsolution.repositories.*;
import com.example.airlinemanagementsolution.services.FlightService;
import jakarta.inject.Inject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.util.Pair;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AirlineManagementSolutionApplicationTests {
    @Inject
    private AirplaneRepository airplaneRepository;
    @Inject
    private AirportRepository airportRepository;
    @Inject
    private FlightRepository flightRepository;
    @Inject
    private UserRepository userRepository;
    @Inject
    private FlightSeatRepository flightSeatRepository;
    @Inject
    private SeatTypeFlightRepository seatTypeFlightRepository;
    @Inject
    private SeatRepository seatRepository;
    @Inject
    private FlightController flightController;
    @Inject
    private FlightService flightService;

    private Airport airport;
    private AirPlane airPlane;
    private List<Seat> seats;
    private User user;
    private User admin;

    @BeforeEach
    public void insertDummyData() {
        airPlane = new AirPlane();
        airPlane.setName("Indigo 1234");
        airPlane.setDescription("Test Indigo");
        airPlane = airplaneRepository.save(airPlane);

        airport = new Airport();
        airport.setName("Delhi Airport");
        airport.setAirportStatus(AirportStatus.OPERATIONAL);
        airport.setSupportedAirplaneTypes(List.of(SupportedAirplaneTypes.AIRBUS,
                SupportedAirplaneTypes.CARGO, SupportedAirplaneTypes.BOEING, SupportedAirplaneTypes.PRIVATE_JET));
        airport = airportRepository.save(airport);

        seats = new ArrayList<>();
        Seat seat1 = new Seat();
        seat1.setName("1A");
        seat1.setSeatType(SeatType.ECONOMY);
        seat1.setAirPlane(airPlane);
        seat1 = seatRepository.save(seat1);
        seats.add(seat1);

        Seat seat2 = new Seat();
        seat2.setName("1B");
        seat2.setSeatType(SeatType.ECONOMY);
        seat2.setAirPlane(airPlane);
        seat2 = seatRepository.save(seat2);
        seats.add(seat2);

        Seat seat3 = new Seat();
        seat3.setName("2A");
        seat3.setSeatType(SeatType.PREMIUM_ECONOMY);
        seat3.setAirPlane(airPlane);
        seat3 = seatRepository.save(seat3);
        seats.add(seat3);

        Seat seat4 = new Seat();
        seat4.setName("2B");
        seat4.setSeatType(SeatType.BUSINESS_CLASS);
        seat4.setAirPlane(airPlane);
        seat4 = seatRepository.save(seat4);
        seats.add(seat4);

        airPlane.setSeats(seats);
        airPlane = airplaneRepository.save(airPlane);

        user = new User();
        user.setName("Test User");
        user.setEmail("test@gmail.com");
        user.setUserType(UserType.CUSTOMER);
        user = userRepository.save(user);

        admin = new User();
        admin.setName("Test Admin");
        admin.setEmail("user@bms.com");
        admin.setUserType(UserType.ADMIN);
        admin = userRepository.save(admin);
    }

    @AfterEach
    public void cleanUp(){
        flightSeatRepository.deleteAll();
        seatTypeFlightRepository.deleteAll();
        flightRepository.deleteAll();
        seatRepository.deleteAll();
        airportRepository.deleteAll();
        airplaneRepository.deleteAll();
        userRepository.deleteAll();
    }

    public Date getTime(int hours){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, hours);
        return calendar.getTime();
    }

    @Test
    public void testCreateFlightSuccess() {
        CreateFlightRequestDto requestDto = new CreateFlightRequestDto();
        requestDto.setAirportId(airport.getId());
        requestDto.setAirplaneId(airPlane.getId());
        requestDto.setStartTime(getTime(1));
        requestDto.setEndTime(getTime(2));
        requestDto.setUserId(admin.getId());
        requestDto.setPricingConfig(List.of(Pair.of(SeatType.ECONOMY, 1000.0),
                Pair.of(SeatType.PREMIUM_ECONOMY, 2000.0),
                Pair.of(SeatType.BUSINESS_CLASS, 10000.0)));
        CreateFlightResponseDto responseDto = flightController.createFlight(requestDto);
        assertNotNull(responseDto, "Create flight response shouldn't be NULL");
        assertEquals(responseDto.getResponseStatus(), ResponseStatus.SUCCESS);

        Flight flight = responseDto.getFlight();
        assertNotNull(flight, "Flight shouldn't be NULL");


        List<FlightSeat> flightSeats = flightSeatRepository.findAll();
        assertEquals(4, flightSeats.size(), "4 flight seats should be present");
    }

    @Test
    public void testCreateFlightFailure() {
        CreateFlightRequestDto requestDto = new CreateFlightRequestDto();
        requestDto.setAirportId(airport.getId());
        requestDto.setAirplaneId(airPlane.getId());
        requestDto.setStartTime(getTime(-1));
        requestDto.setEndTime(getTime(2));
        requestDto.setUserId(admin.getId());
        requestDto.setPricingConfig(List.of(Pair.of(SeatType.ECONOMY, 1000.0),
                Pair.of(SeatType.PREMIUM_ECONOMY, 2000.0),
                Pair.of(SeatType.BUSINESS_CLASS, 10000.0)));
        CreateFlightResponseDto responseDto = flightController.createFlight(requestDto);
        assertNotNull(responseDto, "Create flight response shouldn't be NULL");
        assertEquals(responseDto.getResponseStatus(), ResponseStatus.FAILURE);

        requestDto.setStartTime(getTime(10));
        requestDto.setEndTime(getTime(5));
        responseDto = flightController.createFlight(requestDto);
        System.out.println(responseDto.toString());
        assertNotNull(responseDto, "Create flight response shouldn't be NULL");
        assertEquals(responseDto.getResponseStatus(), ResponseStatus.FAILURE);
        assertNull(responseDto.getFlight(), "Flight should be NULL");
    }

    @Test
    public void testCreateFlight_UnAuthorizedAccess_Failure(){
        CreateFlightRequestDto requestDto = new CreateFlightRequestDto();
        requestDto.setAirportId(airport.getId());
        requestDto.setAirplaneId(airPlane.getId());
        requestDto.setStartTime(getTime(1));
        requestDto.setEndTime(getTime(2));
        requestDto.setUserId(user.getId());
        requestDto.setPricingConfig(List.of(Pair.of(SeatType.ECONOMY, 1000.0),
                Pair.of(SeatType.PREMIUM_ECONOMY, 2000.0),
                Pair.of(SeatType.BUSINESS_CLASS, 10000.0)));
        CreateFlightResponseDto responseDto = flightController.createFlight(requestDto);
        assertNotNull(responseDto, "Create flight response shouldn't be NULL");
        assertEquals(responseDto.getResponseStatus(), ResponseStatus.FAILURE);
        assertNull(responseDto.getFlight(), "Flight should be NULL");
    }
}
