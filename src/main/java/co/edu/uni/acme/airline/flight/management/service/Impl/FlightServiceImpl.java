package co.edu.uni.acme.airline.flight.management.service.Impl;

import co.edu.uni.acme.aerolinea.commons.entity.FlightEntity;
import co.edu.uni.acme.airline.flight.management.repository.FlightRepository;
import co.edu.uni.acme.airline.flight.management.service.IFlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FlightServiceImpl implements IFlightService {

    private final FlightRepository flightRepository;

    @Override
    public List<FlightEntity> getAvailableFlights(LocalDate startDate, LocalDate endDate, String origin, String destination) {
        return flightRepository.findByFlightDateBetweenAndOriginIgnoreCaseAndDestinationIgnoreCase(startDate, endDate, origin, destination);
    }

}
