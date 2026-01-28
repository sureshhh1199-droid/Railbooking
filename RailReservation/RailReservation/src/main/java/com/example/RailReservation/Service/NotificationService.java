package com.example.RailReservation.Service;

public interface NotificationService {
    void sendBookingConfirmation(String email, String pnr);
}
