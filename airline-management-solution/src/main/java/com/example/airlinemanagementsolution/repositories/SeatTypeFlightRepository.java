package com.example.airlinemanagementsolution.repositories;

import com.example.airlinemanagementsolution.models.SeatType;
import com.example.airlinemanagementsolution.models.SeatTypeFlight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatTypeFlightRepository extends JpaRepository<SeatTypeFlight, Integer> {
}
