package com.example.RailReservation.Repository;

import com.example.RailReservation.Entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, String> {
    Optional<Booking> findByPnr(String pnr);
    boolean existsByPnr(String pnr);

    @Query("SELECT b.agentRef, COUNT(b) FROM Booking b GROUP BY b.agentRef")
    List<Object[]> agentProductivity();

    @Query("SELECT COUNT(b) FROM Booking b WHERE DATE(b.createdAt) = :date")
    Long countByDate(@Param("date") LocalDate date);
}
