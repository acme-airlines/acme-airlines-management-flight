package co.edu.uni.acme.airline.flight.management.repository;

import co.edu.uni.acme.aerolinea.commons.entity.CityEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<CityEntity, String> {

    @Query("SELECT c FROM CityEntity c WHERE " +
            "(:code IS NULL OR c.codeCity LIKE %:code%) " +
            "AND (:name IS NULL OR LOWER(c.nameCity) LIKE LOWER(CONCAT('%', :name, '%')))")
    Page<CityEntity> findByCodeAndName(
            @Param("code") String code,
            @Param("name") String name,
            Pageable pageable);

    @Query("""
      SELECT DISTINCT c
        FROM CityEntity c
       WHERE c.codeCity IN (
         SELECT fc.city.codeCity
           FROM FlightCityEntity fc
          WHERE fc.numberFlight IN ('1','2')
       )
    """)
    List<CityEntity> findCitiesWithAvailableFlights();



}
