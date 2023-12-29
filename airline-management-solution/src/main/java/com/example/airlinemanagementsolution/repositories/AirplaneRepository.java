package com.example.airlinemanagementsolution.repositories;

import com.example.airlinemanagementsolution.models.AirPlane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirplaneRepository extends JpaRepository<AirPlane, Integer> {

}
