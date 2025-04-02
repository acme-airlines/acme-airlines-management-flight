package co.edu.uni.acme.airline.flight.management.repository;

import co.edu.uni.acme.aerolinea.commons.entity.FlightEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<FlightEntity, Long> {

    @Query("SELECT f FROM FlightEntity f " +
            "WHERE (f.flightDate >= :startDate) " +
            "AND ( f.flightDate <= :endDate) " +
            "AND (:ignoreOrigin = true OR LOWER(f.origin) = LOWER(:origin)) " +
            "AND (:ignoreDestination = true OR LOWER(f.destination) = LOWER(:destination))")
    List<FlightEntity> findFlightsDynamic(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("ignoreOrigin") boolean ignoreOrigin,
            @Param("origin") String origin,
            @Param("ignoreDestination") boolean ignoreDestination,
            @Param("destination") String destination
    );




}
