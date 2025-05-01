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

    @Query("""
      SELECT DISTINCT f
        FROM FlightEntity f,
             FlightCityEntity fcOrigin,
             FlightCityEntity fcDest
       WHERE f.flightDate BETWEEN
               COALESCE(:startDate, f.flightDate)
           AND COALESCE(:endDate,   f.flightDate)
           AND  fcOrigin.numberFlight   = '1'
           AND fcDest.numberFlight = '4'
         AND LOWER(fcOrigin.city.codeCity)  = LOWER(:origin)
         AND LOWER(fcDest.city.codeCity)    = LOWER(:destination)
    """)
    List<FlightEntity> findFlightsDynamic(
            @Param("startDate")   LocalDate startDate,
            @Param("endDate")     LocalDate endDate,
            @Param("origin")      String    origin,
            @Param("destination") String    destination
    );





}
