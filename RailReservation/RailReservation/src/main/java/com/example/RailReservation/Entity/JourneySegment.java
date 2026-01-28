package com.example.RailReservation.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JourneySegment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String source;
    private String destination;
    private LocalDateTime departure;
    private LocalDateTime arrival;
    private String trainNumber;
    private Integer seat;
    private Double fare;

    @OneToMany(mappedBy = "segment", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Booking> booking;

}

