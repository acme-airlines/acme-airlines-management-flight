package co.edu.uni.acme.airline.flight.management.service;

import co.edu.uni.acme.aerolinea.commons.entity.CityEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICityService {

    List<CityEntity> getAllCities();

    Page<CityEntity> getCitiesByFilters(String code, String name, Pageable pageable);

    List<CityEntity> getCitiesWithAvailableFlights();


}
