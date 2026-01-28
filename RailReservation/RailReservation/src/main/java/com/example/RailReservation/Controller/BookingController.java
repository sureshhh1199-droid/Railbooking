package com.example.RailReservation.Controller;

import com.example.RailReservation.Dto.BookingRequestDTO;
import com.example.RailReservation.Dto.BookingResponseDTO;
import com.example.RailReservation.Service.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService service;

    @PostMapping
    public ResponseEntity<BookingResponseDTO> create(@RequestBody BookingRequestDTO request) {
        return ResponseEntity.ok(service.createBooking(request));
    }
}
