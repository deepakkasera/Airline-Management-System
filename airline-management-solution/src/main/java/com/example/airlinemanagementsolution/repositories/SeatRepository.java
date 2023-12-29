package com.example.airlinemanagementsolution.repositories;

import com.example.airlinemanagementsolution.models.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Integer> {
    @Override
    List<Seat> findAllById(Iterable<Integer> seatIds);

    List<Seat> findAllByAirPlane_Id(int airplaneId);
}
