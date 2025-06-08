package co.edu.uni.acme.airline.flight.management.service;

import co.edu.uni.acme.aerolinea.commons.dto.FlightDTO;
import co.edu.uni.acme.aerolinea.commons.entity.FlightEntity;
import co.edu.uni.acme.airline.flight.management.dto.FlightFilterRequestDTO;
import co.edu.uni.acme.airline.flight.management.dto.FlightResponseDto;

import java.time.LocalDate;
import java.util.List;

public interface IFlightService {

    List<FlightResponseDto> getAvailableFlights(FlightFilterRequestDTO filters) throws Exception;

    void deleteFlightDontPay();

}
