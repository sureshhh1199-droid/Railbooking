package com.example.RailReservation.ServiceImplementation;

import com.example.RailReservation.Entity.Passenger;
import com.example.RailReservation.Repository.PassengerRepository;
import com.example.RailReservation.Service.PassengerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PassengerServiceImpl implements PassengerService {

    private final PassengerRepository repository;


    @Override
    public List<Passenger> getAll() {
        return repository.findAll();
    }

    @Override
    public List<Passenger> searchByName(String name) {
        return repository.findByNameContainingIgnoreCase(name);
    }
}
