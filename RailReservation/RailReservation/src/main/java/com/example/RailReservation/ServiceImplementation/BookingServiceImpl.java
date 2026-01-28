package com.example.RailReservation.ServiceImplementation;


import com.example.RailReservation.Dto.BookingRequestDTO;
import com.example.RailReservation.Dto.BookingResponseDTO;
import com.example.RailReservation.Entity.AuditLog;
import com.example.RailReservation.Entity.Booking;
import com.example.RailReservation.Entity.JourneySegment;
import com.example.RailReservation.Repository.AuditLogRepository;
import com.example.RailReservation.Repository.BookingRepository;
import com.example.RailReservation.Repository.JourneySegmentRepository;
import com.example.RailReservation.Repository.PassengerRepository;
import com.example.RailReservation.Service.AuditLogService;
import com.example.RailReservation.Service.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
@AllArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository repo;
    private final JourneySegmentRepository journeyRepo;
    private final PassengerRepository passengerRepo;
    private final AuditLogService auditLogService;

    @Override
    public BookingResponseDTO createBooking(BookingRequestDTO request) {

        JourneySegment train = journeyRepo
                .getBySourceAndDestinationAndDeparture(
                        request.getSource(),
                        request.getDestination(),
                        request.getDepartureDate()
                );

        if (train == null) {
            throw new RuntimeException("No journey found for given route and time");
        }

        Booking booking = new Booking();
        booking.setPnr(generatePNR());
        booking.setStatus("CONFIRMED");
        booking.setPaymentStatus("SUCCESS");
        booking.setAgentRef(request.getAgentRef());
        booking.setCreatedAt(LocalDateTime.now());
        booking.setPassengers(request.getPassengers());

        booking.setSegment(train);
        if (train.getBooking() == null) {
            train.setBooking(new ArrayList<>());
        }
        train.getBooking().add(booking);

        booking.setTotalFare(train.getFare() * request.getPassengers().size());


        booking.setTotalFare(train.getFare() * request.getPassengers().size());

//        if (booking.getPassengers() != null) {
//            booking.getPassengers().forEach(p -> p.set(booking));
//        }

        repo.save(booking);

        train.setSeat(train.getSeat() - request.getPassengers().size());
        journeyRepo.save(train);

        auditLogService.audit("Booked", booking.getPnr(), LocalDateTime.now(), booking.getAgentRef(),booking.getPassengers().size());

        BookingResponseDTO dto = new BookingResponseDTO();
        dto.pnr = booking.getPnr();
        dto.status = booking.getStatus();
        dto.totalFare = booking.getTotalFare();

        return dto;
    }

    private String generatePNR() {
        return "PNR" + System.currentTimeMillis();
    }
}