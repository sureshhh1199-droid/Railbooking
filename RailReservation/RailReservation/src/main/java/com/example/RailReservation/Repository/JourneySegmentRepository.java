package com.example.RailReservation.Repository;

import com.example.RailReservation.Entity.JourneySegment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface JourneySegmentRepository extends JpaRepository<JourneySegment, String> {

    List<JourneySegment> findByTrainNumber(String trainNumber);

    List<JourneySegment> findBySourceAndDestination(String source, String destination);

    JourneySegment getBySourceAndDestinationAndDeparture(String source, String destination, LocalDateTime departureDate);

//    Optional<JourneySegment> findFirstByBooking_Pnr(String pnr);

}
