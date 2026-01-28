package com.example.RailReservation.Controller;

import com.example.RailReservation.Entity.Passenger;
import com.example.RailReservation.Service.PassengerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/passengers")
public class PassengerController {

    private final PassengerService service;


    // Get All Passengers
    @GetMapping
    public ResponseEntity<List<Passenger>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }


    // Search by Name
    @GetMapping("/search")
    public ResponseEntity<List<Passenger>> search(@RequestParam String name) {
        return ResponseEntity.ok(service.searchByName(name));
    }
}
