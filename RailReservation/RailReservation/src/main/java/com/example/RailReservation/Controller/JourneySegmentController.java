package com.example.RailReservation.Controller;

import com.example.RailReservation.Dto.JourneySegmentDTO;
import com.example.RailReservation.Entity.JourneySegment;
import com.example.RailReservation.Service.JourneySegmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/segments")
public class JourneySegmentController {

    private final JourneySegmentService service;


    // Admin / GDS: Add train segment
    @PostMapping
    public ResponseEntity<JourneySegment> create(@RequestBody JourneySegmentDTO segment) {
        return ResponseEntity.ok(service.create(segment));
    }

    // View all segments
    @GetMapping
    public ResponseEntity<List<JourneySegment>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    // Search by route
    @GetMapping("/search")
    public ResponseEntity<List<JourneySegment>> search(
            @RequestParam String source,
            @RequestParam String destination) {

        return ResponseEntity.ok(service.search(source, destination));
    }
}
