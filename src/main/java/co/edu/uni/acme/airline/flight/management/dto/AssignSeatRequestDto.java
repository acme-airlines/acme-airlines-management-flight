package co.edu.uni.acme.airline.flight.management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssignSeatRequestDto {

    private String passengerCode;

    private String seatCode;

}
