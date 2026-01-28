package com.example.RailReservation.Exception;

public class BookingNotFoundException extends RuntimeException {
    public BookingNotFoundException(String pnr) {
        super("Booking not found for PNR: " + pnr);
    }
}
