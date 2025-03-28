package co.edu.uni.acme.airline.flight.management.service;

import co.edu.uni.acme.aerolinea.commons.entity.FlightEntity;
import java.time.LocalDate;
import java.util.List;

public interface IFlightService {

    List<FlightEntity> getAvailableFlights(LocalDate startDate, LocalDate endDate, String origin, String destination);

}
