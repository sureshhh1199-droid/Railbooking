package com.example.RailReservation.Service;

import com.example.RailReservation.Dto.PnrResponseDTO;

public interface PnrService {
    PnrResponseDTO getByPnr(String pnr);
}
