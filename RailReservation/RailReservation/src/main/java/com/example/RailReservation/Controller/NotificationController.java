package com.example.RailReservation.Controller;

import com.example.RailReservation.Service.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/notify")
public class NotificationController {

    private final JavaMailSender MailSender;

    private final NotificationService service;

    @PostMapping("/{pnr}")
    public ResponseEntity<String> notifyBooking(@PathVariable String pnr, @RequestParam String email) {
        service.sendBookingConfirmation(email, pnr);
        return ResponseEntity.ok().build();
    }

//    @PostMapping("/mail")
//    public String mail(@RequestParam ArrayList<String> email){
//
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setSubject("Testing");
//        message.setText("hello , hii");
//        for(int i=0;i<email.size();i++){
//            message.setTo(email.get(i));
//            MailSender.send(message);
//        }
//        return "mail send successfully";
//    }
}
