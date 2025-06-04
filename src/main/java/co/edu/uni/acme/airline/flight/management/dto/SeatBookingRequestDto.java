package co.edu.uni.acme.airline.flight.management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeatBookingRequestDto {
    /**
     * Código del vuelo (p.ej. “AC1001”)
     */
    private String codeFlight;

    /**
     * Código del asiento-vuelo (p.ej. “SF001”)
     */
    private String codeSeatFlight;

    /**
     * Código del pasajero (p.ej. “PAX0001”)
     */
    private String codePassenger;
}
