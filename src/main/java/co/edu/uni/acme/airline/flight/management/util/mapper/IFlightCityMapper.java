package co.edu.uni.acme.airline.flight.management.util.mapper;

import co.edu.uni.acme.aerolinea.commons.dto.FlightCityDTO;
import co.edu.uni.acme.aerolinea.commons.dto.FlightDTO;
import co.edu.uni.acme.aerolinea.commons.entity.FlightCityEntity;
import co.edu.uni.acme.aerolinea.commons.entity.FlightEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IFlightCityMapper {
    FlightCityDTO entityToDto(FlightCityEntity entity);

    FlightCityEntity dtoToEntity(FlightCityDTO dto);

    List<FlightCityDTO> listEntityToListDto(List<FlightCityEntity> entity);

    List<FlightCityEntity>listDtoToListEntity(List<FlightCityDTO> dto);


}
