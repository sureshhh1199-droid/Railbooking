package com.example.RailReservation.ServiceImplementation;

import com.example.RailReservation.Dto.PnrResponseDTO;
import com.example.RailReservation.Entity.Booking;
import com.example.RailReservation.Exception.BookingNotFoundException;
import com.example.RailReservation.Repository.BookingRepository;
import com.example.RailReservation.Service.AuditLogService;
import com.example.RailReservation.Service.PnrService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class PnrServiceImpl implements PnrService {

    private final BookingRepository bookingRepository;
    private final AuditLogService auditLogService;

    @Override
    public PnrResponseDTO getByPnr(String pnr) {
        Booking booking = bookingRepository.findByPnr(pnr)
                .orElseThrow(() -> new BookingNotFoundException(pnr));

        PnrResponseDTO dto = new PnrResponseDTO();
        dto.pnr = booking.getPnr();
        dto.status = booking.getStatus();
        dto.totalFare = booking.getTotalFare();
        dto.segment = booking.getSegment();
        dto.passengers = booking.getPassengers();

        auditLogService.audit("Viewed PNR", booking.getPnr(), LocalDateTime.now(), booking.getAgentRef(),booking.getPassengers().size());


        return dto;
    }
}
