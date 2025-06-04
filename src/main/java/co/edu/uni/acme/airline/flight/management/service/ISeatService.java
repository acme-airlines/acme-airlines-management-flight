package co.edu.uni.acme.airline.flight.management.service;

import co.edu.uni.acme.aerolinea.commons.dto.SeatFlightDTO;
import co.edu.uni.acme.airline.flight.management.dto.AssignSeatRequestDto;

import java.util.List;

public interface ISeatService {

     List<SeatFlightDTO> getSeatsByFlight(String flightCode);

}
