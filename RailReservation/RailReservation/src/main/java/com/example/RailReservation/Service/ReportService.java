package com.example.RailReservation.Service;

import java.time.LocalDate;
import java.util.List;

public interface ReportService {
    List<Object[]> agentProductivity();
    Long dailyBookings(LocalDate date);
}
