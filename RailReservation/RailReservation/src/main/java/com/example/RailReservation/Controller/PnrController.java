package com.example.RailReservation.Controller;

import com.example.RailReservation.Dto.PnrResponseDTO;
import com.example.RailReservation.Service.PnrService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pnr")
public class PnrController {

    private final PnrService service;

    public PnrController(PnrService service) {
        this.service = service;
    }

    @GetMapping("/{pnr}")
    public ResponseEntity<PnrResponseDTO> fetch(@PathVariable String pnr) {
        return ResponseEntity.ok(service.getByPnr(pnr));
    }
}
