package com.example.RailReservation.Service;

import com.example.RailReservation.Dto.JourneySegmentDTO;
import com.example.RailReservation.Entity.JourneySegment;

import java.util.List;

public interface JourneySegmentService {

    JourneySegment create(JourneySegmentDTO segment);

    List<JourneySegment> getAll();

    List<JourneySegment> search(String source, String destination);
}
