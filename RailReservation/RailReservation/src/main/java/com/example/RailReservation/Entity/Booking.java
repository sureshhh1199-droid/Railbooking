package com.example.RailReservation.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "bookings")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String pnr;

    private String status; // PENDING, CONFIRMED, CANCELLED, EXCHANGED

    private String paymentStatus; // PENDING, SUCCESS, FAILED

    private Double totalFare;

    private String agentRef;

    private LocalDateTime createdAt;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Passenger> passengers;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonManagedReference
    @JoinColumn(name = "journeyId")
    private JourneySegment segment;
}

