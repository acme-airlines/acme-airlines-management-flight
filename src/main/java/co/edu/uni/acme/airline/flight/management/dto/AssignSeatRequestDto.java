package co.edu.uni.acme.airline.flight.management.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssignSeatRequestDto {

    @NotBlank
    private String passengerCode;

    @NotBlank
    private String seatCode;


}
