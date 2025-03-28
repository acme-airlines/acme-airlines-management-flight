package co.edu.uni.acme.airline.flight.management.controller;

import co.edu.uni.acme.aerolinea.commons.entity.CityEntity;
import co.edu.uni.acme.airline.flight.management.service.ICityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cities")
@RequiredArgsConstructor
public class CityController {

    private final ICityService cityService;

    @GetMapping("/all")
    public ResponseEntity<List<CityEntity>> getAllCities() {
        return ResponseEntity.ok(cityService.getAllCities());
    }

    @GetMapping("/filter")
    public ResponseEntity<Page<CityEntity>> filterCities(
            @RequestParam(required = false) String code,
            @RequestParam(required = false) String name,
            Pageable pageable) {
        return ResponseEntity.ok(cityService.getCitiesByFilters(code, name, pageable));
    }
}
