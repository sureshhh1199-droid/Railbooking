package com.example.RailReservation.Dto;

import com.example.RailReservation.Entity.Passenger;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingRequestDTO {
    private String source;
    private String destination;
    private LocalDateTime departureDate;
    private List<Passenger> passengers;
    private String agentRef;
}

