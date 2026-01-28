package com.example.RailReservation.ServiceImplementation;

import com.example.RailReservation.Entity.AuditLog;
import com.example.RailReservation.Repository.AuditLogRepository;
import com.example.RailReservation.Service.AuditLogService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class AuditLogServiceImpl implements AuditLogService {

    private final AuditLogRepository auditLogRepository;

    @Override
    public void audit(String action, String pnr, LocalDateTime timeStamp, String performedBy, Integer passengerCount) {
        AuditLog auditLog = new AuditLog();
        auditLog.setAction(action);
        auditLog.setPnr(pnr);
        auditLog.setTimestamp(timeStamp);
        auditLog.setPerformedBy(performedBy);
        auditLog.setPassengerCount(passengerCount);
        auditLogRepository.save(auditLog);
    }
}
