package co.edu.uni.acme.airline.flight.management.service.Impl;

import co.edu.uni.acme.aerolinea.commons.entity.CityEntity;
import co.edu.uni.acme.airline.flight.management.repository.CityRepository;
import co.edu.uni.acme.airline.flight.management.service.ICityService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements ICityService {

    private final CityRepository cityRepository;

    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public List<CityEntity> getAllCities() {
        return cityRepository.findAll();
    }

    @Override
    public Page<CityEntity> getCitiesByFilters(String code, String name, Pageable pageable) {
        return cityRepository.findByCodeAndName(code, name, pageable);
    }

    @Override
    public List<CityEntity> getCitiesWithAvailableFlights() {
        return cityRepository.findCitiesWithAvailableFlights();
    }

}
