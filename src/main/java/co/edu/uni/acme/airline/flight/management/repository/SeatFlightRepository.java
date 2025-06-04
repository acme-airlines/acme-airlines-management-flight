package co.edu.uni.acme.airline.flight.management.repository;

import co.edu.uni.acme.aerolinea.commons.entity.SeatFlightEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SeatFlightRepository extends JpaRepository<SeatFlightEntity, String> {

    List<SeatFlightEntity> findByCodeFlightFk_codeFlight(String codeFlightFk);

    Optional<SeatFlightEntity> findByCodeSeatFlight(String codeSeatFlight);
}
