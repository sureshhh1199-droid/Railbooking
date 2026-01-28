package com.example.RailReservation.ServiceImplementation;

import com.example.RailReservation.Entity.Booking;
import com.example.RailReservation.Entity.JourneySegment;
import com.example.RailReservation.Exception.BookingNotFoundException;
import com.example.RailReservation.Repository.BookingRepository;
import com.example.RailReservation.Repository.JourneySegmentRepository;
import com.example.RailReservation.Service.AuditLogService;
import com.example.RailReservation.Service.CancellationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class CancellationServiceImpl implements CancellationService {

    private final BookingRepository repo;
    private final JourneySegmentRepository journeySegmentRepo;
    private final AuditLogService auditLogService;


    @Override
    public void cancelBooking(String pnr) {
        Booking booking = repo.findByPnr(pnr)
                .orElseThrow(() -> new BookingNotFoundException(pnr));

        JourneySegment segment= booking.getSegment();
        segment.setSeat(segment.getSeat()+booking.getPassengers().size());
        journeySegmentRepo.save(segment);
        booking.setStatus("CANCELLED");
        repo.save(booking);

        auditLogService.audit("Cancelled", booking.getPnr(), LocalDateTime.now(), booking.getAgentRef(),booking.getPassengers().size());


    }
}
