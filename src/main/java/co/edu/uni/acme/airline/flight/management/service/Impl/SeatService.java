package co.edu.uni.acme.airline.flight.management.service.Impl;

import co.edu.uni.acme.aerolinea.commons.dto.*;
import co.edu.uni.acme.aerolinea.commons.entity.SeatFlightEntity;
import co.edu.uni.acme.airline.flight.management.dto.AssignSeatRequestDto;
import co.edu.uni.acme.airline.flight.management.repository.SeatFlightRepository;
import co.edu.uni.acme.airline.flight.management.service.ISeatService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class SeatService implements ISeatService {

    private final SeatFlightRepository seatFlightRepo;
    private final UserAuditService userAuditService;

    /**
     * Retorna la lista de SeatFlightDto (estado de asientos) para un vuelo dado.
     */
    public List<SeatFlightDTO> getSeatsByFlight(String flightCode) {
        List<SeatFlightEntity> entities = seatFlightRepo.findByCodeFlightFk_codeFlight(flightCode);

        UserAuditDto userAuditDto = new UserAuditDto();
        UserDTO userDTO = new UserDTO();
        userDTO.setCodeUser("U001");
        userAuditDto.setAction("SELECT to seat_flight");
        userAuditDto.setCodeUser(userDTO);
        userAuditService.saveAuditUser(userAuditDto);

        return entities.stream()
                .map(e -> {
                    // Crear DTO de vuelo a partir de la entidad
                    FlightDTO flightDTO = new FlightDTO();
                    flightDTO.setCodeFlight(e.getCodeFlightFk().getCodeFlight());

                    // Crear DTO de asiento a partir de la entidad
                    SeatDTO seatDTO = new SeatDTO();
                    seatDTO.setCodeSeat(e.getCodeSeatFk().getCodeSeat());

                    // Crear y devolver SeatFlightDTO
                    return new SeatFlightDTO(
                            e.getCodeSeatFlight(),
                            flightDTO,
                            seatDTO,
                            e.getStatus()
                    );
                })
                .collect(Collectors.toList());
    }

}
