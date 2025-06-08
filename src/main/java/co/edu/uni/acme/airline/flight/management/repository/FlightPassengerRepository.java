package co.edu.uni.acme.airline.flight.management.repository;

import co.edu.uni.acme.aerolinea.commons.entity.PassengerFlightEntity;
import co.edu.uni.acme.aerolinea.commons.entity.PassengerFlightId;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightPassengerRepository extends JpaRepository<PassengerFlightEntity, PassengerFlightId> {

    @Query(value = """
    SELECT
      pf.code_passenger,
      pf.code_flight
    FROM acme_airlines.passenger_flight pf
    LEFT JOIN acme_airlines.payment_flight_user pfu
      ON pf.code_passenger = pfu.code_passenger_fk
    WHERE pfu.code_passenger_fk IS NULL;
    """, nativeQuery = true)
    List<Object[]> findFlightPassengerDontPay();

    @Query(value = """
     DELETE FROM acme_airlines.passenger_flight pf where pf.code_passenger = :codePassenger and pf.code_flight = :codeFlight
    """, nativeQuery = true)
    @Modifying
    @Transactional
    void deleteFlightPassengerDontPay(@Param("codeFlight") String codeFlight, @Param("codePassenger") String codePassenger);

}
