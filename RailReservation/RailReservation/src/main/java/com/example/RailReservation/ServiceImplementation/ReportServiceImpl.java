package com.example.RailReservation.ServiceImplementation;

import com.example.RailReservation.Repository.BookingRepository;
import com.example.RailReservation.Service.ReportService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final BookingRepository bookingRepository;

    @Override
    public List<Object[]> agentProductivity() {
        return bookingRepository.agentProductivity();
    }

    @Override
    public Long dailyBookings(LocalDate date) {
        return bookingRepository.countByDate(date);
    }
}
