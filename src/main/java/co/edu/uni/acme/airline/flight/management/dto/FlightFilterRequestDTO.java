package co.edu.uni.acme.airline.flight.management.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FlightFilterRequestDTO {

    private LocalDate startDate;

    private LocalDate endDate;

    private String origin;

    private String destination;
}
