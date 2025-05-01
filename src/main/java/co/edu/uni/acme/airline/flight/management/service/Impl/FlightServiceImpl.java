package co.edu.uni.acme.airline.flight.management.service.Impl;

import co.edu.uni.acme.aerolinea.commons.dto.FlightCityDTO;
import co.edu.uni.acme.aerolinea.commons.dto.FlightDTO;
import co.edu.uni.acme.aerolinea.commons.entity.FlightEntity;
import co.edu.uni.acme.airline.flight.management.dto.FlightFilterRequestDTO;
import co.edu.uni.acme.airline.flight.management.dto.FlightResponseDto;
import co.edu.uni.acme.airline.flight.management.repository.FlightCityRepository;
import co.edu.uni.acme.airline.flight.management.repository.FlightRepository;
import co.edu.uni.acme.airline.flight.management.service.IFlightService;
import co.edu.uni.acme.airline.flight.management.util.mapper.IFlightCityMapper;
import co.edu.uni.acme.airline.flight.management.util.mapper.IFlightMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class FlightServiceImpl implements IFlightService {

    private final FlightRepository flightRepository;

    private final IFlightMapper iFlightMapper;

    private final FlightCityRepository flightCityRepository;

    private final IFlightCityMapper flightCityMapper;

    @Override
    public List<FlightResponseDto> getAvailableFlights(FlightFilterRequestDTO filters) throws Exception {
        if (filters.getStartDate() != null && filters.getStartDate().isBefore(LocalDate.now())) {
            throw new Exception("La fecha de inicio no puede ser anterior a la fecha actual.");
        }
        List<FlightDTO> flights = iFlightMapper.listEntityToListDto(flightRepository.findFlightsDynamic(filters.getStartDate(), filters.getEndDate(),  filters.getOrigin(),filters.getDestination()));

        if(flights.isEmpty()){
            throw new Exception("No hay vuelos disponibles para los criterios seleccionados.");
        }
        List<FlightResponseDto> responseDtos = new ArrayList<>();
        for(FlightDTO flight : flights){
            List<FlightCityDTO> flightCities = flightCityMapper.listEntityToListDto(flightCityRepository.findAllByFlight_CodeFlight(flight.getCodeFlight()));
            FlightResponseDto flightResponse = new FlightResponseDto();
            Map<String, String> destinations = new HashMap<>();
            for(FlightCityDTO flightCityDTO : flightCities){
                destinations.put(flightCityDTO.getNumberFlight(), flightCityDTO.getCity().getNameCity());
            }
            flightResponse.setDestination(destinations);
            flightResponse.setValueFlight("0");
            flightResponse.setStartTime(String.valueOf(flight.getFlightStartTime()));
            flightResponse.setEndTime(String.valueOf(flight.getFlightEndTime()));
            responseDtos.add(flightResponse);
        }

        return responseDtos;
    }

}
