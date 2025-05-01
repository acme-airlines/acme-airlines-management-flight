package co.edu.uni.acme.airline.flight.management.repository;

import co.edu.uni.acme.aerolinea.commons.entity.FlightCityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightCityRepository extends JpaRepository<FlightCityEntity, String> {

    List<FlightCityEntity> findAllByFlight_CodeFlight(String codeFlight);

}
