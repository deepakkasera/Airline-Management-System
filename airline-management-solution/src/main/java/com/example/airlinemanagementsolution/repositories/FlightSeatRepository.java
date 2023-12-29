package com.example.airlinemanagementsolution.repositories;

import com.example.airlinemanagementsolution.models.FlightSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightSeatRepository extends JpaRepository<FlightSeat, Integer> {

}
