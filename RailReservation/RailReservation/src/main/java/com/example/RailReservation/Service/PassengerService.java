package com.example.RailReservation.Service;

import com.example.RailReservation.Entity.Passenger;

import java.util.List;

public interface PassengerService {

    List<Passenger> getAll();

    List<Passenger> searchByName(String name);
}
