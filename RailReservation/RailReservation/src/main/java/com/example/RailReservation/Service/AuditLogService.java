package com.example.RailReservation.Service;

import java.time.LocalDateTime;

public interface AuditLogService {
    void audit(String action, String pnr, LocalDateTime timeStamp, String performedBy,Integer passengerCount);
}
