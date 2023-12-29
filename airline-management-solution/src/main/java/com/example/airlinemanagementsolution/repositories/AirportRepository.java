package com.example.airlinemanagementsolution.repositories;

import com.example.airlinemanagementsolution.models.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Integer> {

}
