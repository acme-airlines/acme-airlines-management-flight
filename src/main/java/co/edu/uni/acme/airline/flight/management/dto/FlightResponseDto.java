package co.edu.uni.acme.airline.flight.management.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class FlightResponseDto {

    private Map<String, String> destination;

    private String startTime;

    private String endTime;

    private String valueFlight;

}
