package co.edu.uni.acme.airline.flight.management.controller;

import co.edu.uni.acme.aerolinea.commons.dto.SeatFlightDTO;
import co.edu.uni.acme.aerolinea.commons.dto.UserAuditDto;
import co.edu.uni.acme.airline.flight.management.service.ISeatService;
import co.edu.uni.acme.airline.flight.management.service.Impl.SeatBookingService;
import co.edu.uni.acme.airline.flight.management.service.Impl.UserAuditService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user-audit")
@RequiredArgsConstructor
public class UserAuditController {

    private final UserAuditService userAuditService;

    @PostMapping("/save")
    public ResponseEntity<String> saveUserAudit(@RequestBody UserAuditDto userAuditDto) {
        userAuditService.saveAuditUser(userAuditDto);
        return ResponseEntity.ok("the audit was saved correctly");
    }

}
