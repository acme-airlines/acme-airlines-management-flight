package co.edu.uni.acme.airline.flight.management.controller;

import co.edu.uni.acme.aerolinea.commons.dto.SeatFlightDTO;
import co.edu.uni.acme.airline.flight.management.dto.AssignSeatRequestDto;
import co.edu.uni.acme.airline.flight.management.dto.RespuestaDto;
import co.edu.uni.acme.airline.flight.management.dto.SeatBookingRequestDto;
import co.edu.uni.acme.airline.flight.management.service.ISeatService;
import co.edu.uni.acme.airline.flight.management.service.Impl.SeatBookingService;
import co.edu.uni.acme.airline.flight.management.service.Impl.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/seat")
@RequiredArgsConstructor
public class SeatController {

    private final ISeatService seatService;
    private final SeatBookingService seatBookingService;

    /**
     * GET /api/v1/flights/{flightCode}/seats
     * → Retorna todos los asientos y su estado (FREE u OCCUPIED) para el vuelo dado.
     */
    @GetMapping("/{flightCode}")
    public ResponseEntity<List<SeatFlightDTO>> getSeatsByFlight(
            @PathVariable("flightCode") String flightCode) {

        List<SeatFlightDTO> seats = seatService.getSeatsByFlight(flightCode);
        return ResponseEntity.ok(seats);
    }


    @PostMapping("/book")
    public ResponseEntity<RespuestaDto> bookSeats(@RequestBody List<SeatBookingRequestDto> listaDto) {
        RespuestaDto resp = seatBookingService.bookSeat(listaDto);
        if ("SUCCESS".equals(resp.getStatus())) {
            return ResponseEntity.ok(resp);
        } else {
            return ResponseEntity.badRequest().body(resp);
        }
    }

}
