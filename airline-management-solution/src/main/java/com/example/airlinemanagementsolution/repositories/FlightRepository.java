package com.example.airlinemanagementsolution.repositories;

import com.example.airlinemanagementsolution.models.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer> {
    @Override
    Optional<Flight> findById(Integer showId);
}
