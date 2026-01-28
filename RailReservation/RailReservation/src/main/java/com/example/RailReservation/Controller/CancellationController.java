package com.example.RailReservation.Controller;

import com.example.RailReservation.Service.CancellationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/cancel")
public class CancellationController {

    private final CancellationService cancellationService;

    @PostMapping("/{pnr}")
    public ResponseEntity<String> cancel(@PathVariable String pnr) {
        cancellationService.cancelBooking(pnr);
        return ResponseEntity.ok("Booking cancelled successfully for PNR: " + pnr);
    }
}
