package com.example.RailReservation.ServiceImplementation;

import com.example.RailReservation.Entity.Booking;
import com.example.RailReservation.Repository.BookingRepository;
import com.example.RailReservation.Service.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.time.ZoneId;
import java.util.Date;


@Service
@AllArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final JavaMailSender mailSender;
    private BookingRepository bookingRepository;

    @Override
    public void sendBookingConfirmation(String email, String pnr) {

        Booking booking = bookingRepository.findByPnr(pnr).orElseThrow(()->new RuntimeException("Train not found"));


        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Rail Booking Status");
        String mailBody =
                        "Booking Status: "+ booking.getStatus()+"!\n\n" +
                        "PNR: " + booking.getPnr() + "\n" +
                        "Source: " + booking.getSegment().getSource() + "\n" +
                        "Destination: " + booking.getSegment().getDestination() + "\n" +
                        "Departure: " + booking.getSegment().getDeparture() + "\n" +
                        "Passengers: " + booking.getPassengers() + "\n";
        message.setText(mailBody);
        Date sentDate = Date.from(
                booking.getSegment()
                        .getDeparture()
                        .atZone(ZoneId.systemDefault())
                        .toInstant()
        );
        message.setSentDate(sentDate);

        mailSender.send(message);

    }

}
