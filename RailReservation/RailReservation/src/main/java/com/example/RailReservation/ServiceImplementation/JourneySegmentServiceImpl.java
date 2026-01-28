package com.example.RailReservation.ServiceImplementation;

import com.example.RailReservation.Dto.JourneySegmentDTO;
import com.example.RailReservation.Entity.JourneySegment;
import com.example.RailReservation.Repository.JourneySegmentRepository;
import com.example.RailReservation.Service.JourneySegmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class JourneySegmentServiceImpl implements JourneySegmentService {

    private final JourneySegmentRepository repository;


    @Override
    public JourneySegment create(JourneySegmentDTO segment) {
        JourneySegment journeySegment = new JourneySegment();
        journeySegment.setSource(segment.getSource());
        journeySegment.setDestination(segment.getDestination());
        journeySegment.setDeparture(segment.getDeparture());
        journeySegment.setArrival(segment.getArrival());
        journeySegment.setTrainNumber(segment.getTrainNumber());
        journeySegment.setSeat(segment.getSeat());
        journeySegment.setFare(segment.getFare());
        return repository.save(journeySegment);
    }

    @Override
    public List<JourneySegment> getAll() {
        return repository.findAll();
    }

    @Override
    public List<JourneySegment> search(String source, String destination) {
        return repository.findBySourceAndDestination(source, destination);
    }
}
