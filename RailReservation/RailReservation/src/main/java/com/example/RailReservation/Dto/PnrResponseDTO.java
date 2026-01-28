package com.example.RailReservation.Dto;

import com.example.RailReservation.Entity.JourneySegment;
import com.example.RailReservation.Entity.Passenger;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PnrResponseDTO {

    public String pnr;
    public String status;
    public Double totalFare;

    public List<Passenger> passengers;
    public JourneySegment segment;
}
