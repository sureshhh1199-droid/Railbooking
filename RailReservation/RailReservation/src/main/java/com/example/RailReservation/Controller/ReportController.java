package com.example.RailReservation.Controller;

import com.example.RailReservation.Service.ReportService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/reports")
public class ReportController {

    private final ReportService service;

    @GetMapping("/agents")
    public ResponseEntity<List<Object[]>> agentReport() {
        return ResponseEntity.ok(service.agentProductivity());
    }

    @GetMapping("/daily")
    public ResponseEntity<Long> daily(@RequestParam String date) {
        return ResponseEntity.ok(service.dailyBookings(LocalDate.parse(date)));
    }
}
