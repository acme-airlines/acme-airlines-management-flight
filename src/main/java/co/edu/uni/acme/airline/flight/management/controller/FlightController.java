package co.edu.uni.acme.airline.flight.management.controller;

import co.edu.uni.acme.aerolinea.commons.entity.FlightEntity;
import co.edu.uni.acme.airline.flight.management.dto.FlightFilterRequestDTO;
import co.edu.uni.acme.airline.flight.management.service.IFlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/public/flights")
@RequiredArgsConstructor
public class FlightController {

    private final IFlightService flightService;

    @PostMapping("/filter")
    public ResponseEntity<?> getAvailableFlights(@RequestBody FlightFilterRequestDTO filters) {
        if (filters.getStartDate() != null && filters.getStartDate().isBefore(LocalDate.now())) {
            return ResponseEntity.badRequest().body("La fecha de inicio no puede ser anterior a la fecha actual.");
        }

        List<FlightEntity> flights = flightService.getAvailableFlights(
                filters.getStartDate(),
                filters.getEndDate(),
                filters.getOrigin(),
                filters.getDestination()
        );

        if (flights.isEmpty()) {
            return ResponseEntity.ok().body("No hay vuelos disponibles para los criterios seleccionados.");
        }

        return ResponseEntity.ok(flights);
    }


}
