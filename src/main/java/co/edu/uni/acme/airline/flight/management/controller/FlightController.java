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

import java.util.List;

@RestController
@RequestMapping("/api/v1/flights")
@RequiredArgsConstructor
public class FlightController {

    private final IFlightService flightService;

    @PostMapping("/filter")
    public ResponseEntity<List<FlightEntity>> getAvailableFlights(@RequestBody FlightFilterRequestDTO filters) {
        List<FlightEntity> flights = flightService.getAvailableFlights(
                filters.getStartDate(),
                filters.getEndDate(),
                filters.getOrigin(),
                filters.getDestination()
        );

        return ResponseEntity.ok(flights);

    }

}
