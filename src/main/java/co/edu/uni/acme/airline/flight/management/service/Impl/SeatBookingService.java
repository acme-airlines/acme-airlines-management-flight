package co.edu.uni.acme.airline.flight.management.service.Impl;

import co.edu.uni.acme.aerolinea.commons.entity.SeatFlightEntity;
import co.edu.uni.acme.airline.flight.management.dto.RespuestaDto;
import co.edu.uni.acme.airline.flight.management.dto.SeatBookingRequestDto;
import co.edu.uni.acme.airline.flight.management.repository.SeatFlightRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SeatBookingService {

    private final SeatFlightRepository seatFlightRepo;

    /**
     * Procesa un listado de reservas de asiento. Por cada elemento en la lista:
     *   1) Verifica que el asiento-vuelo exista.
     *   2) Verifica que dicho asiento pertenezca al vuelo indicado.
     *   3) Verifica que el asiento esté “available”.
     *   4) Lo marca como “reserved” y lo guarda.
     *
     * Si alguna verificación falla, se retorna un RespuestaDto con status = "FAILED"
     * y mensaje explicativo. En ese caso no se procesan los siguientes elementos.
     *
     * Si todas las reservas pasan correctamente, se retorna un RespuestaDto con status = "SUCCESS".
     */
    @Transactional
    public RespuestaDto bookSeat(List<SeatBookingRequestDto> solicitudes) {
        for (SeatBookingRequestDto dto : solicitudes) {
            // 1) Verificar que el asiento exista
            Optional<SeatFlightEntity> optSeat = seatFlightRepo.findByCodeSeatFlight(dto.getCodeSeatFlight());
            if (optSeat.isEmpty()) {
                return new RespuestaDto("FAILED",
                        "Código de asiento-vuelo no encontrado: " + dto.getCodeSeatFlight());
            }

            SeatFlightEntity sf = optSeat.get();

            if (!"available".equalsIgnoreCase(seatFlightRepo.findByCodeSeatFlight(dto.getCodeSeatFlight()).get().getStatus())) {
                return new RespuestaDto("FAILED",
                        "Asiento no disponible: " + dto.getCodeSeatFlight());
            }

            sf.setStatus("reserved");
            seatFlightRepo.save(sf);
        }

        return new RespuestaDto("SUCCESS", "Todos los asientos reservados correctamente");
    }

}
