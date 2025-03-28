package co.edu.uni.acme.airline.flight.management.repository;

import co.edu.uni.acme.aerolinea.commons.entity.FlightEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<FlightEntity, Long> {

    List<FlightEntity> findByFlightDateBetweenAndOriginIgnoreCaseAndDestinationIgnoreCase(
            LocalDate startDate, LocalDate endDate, String origin, String destination
    );

}
