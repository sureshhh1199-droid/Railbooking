package com.example.RailReservation.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JourneySegmentDTO {

    private String source;
    private String destination;
    private LocalDateTime departure;
    private LocalDateTime arrival;
    private String trainNumber;
    private Integer seat;
    private Double fare;
}
