package com.example.RailReservation.Repository;

import com.example.RailReservation.Entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, String> {

    List<Passenger> findByNameContainingIgnoreCase(String name);
}
