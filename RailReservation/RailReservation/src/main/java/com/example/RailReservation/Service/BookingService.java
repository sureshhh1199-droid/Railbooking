package com.example.RailReservation.Service;

import com.example.RailReservation.Dto.BookingRequestDTO;
import com.example.RailReservation.Dto.BookingResponseDTO;

public interface BookingService {
    BookingResponseDTO createBooking(BookingRequestDTO request);
}
