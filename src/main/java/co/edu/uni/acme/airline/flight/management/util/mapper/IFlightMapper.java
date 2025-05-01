package co.edu.uni.acme.airline.flight.management.util.mapper;

import co.edu.uni.acme.aerolinea.commons.dto.FlightDTO;
import co.edu.uni.acme.aerolinea.commons.dto.PassengerDTO;
import co.edu.uni.acme.aerolinea.commons.entity.FlightEntity;
import co.edu.uni.acme.aerolinea.commons.entity.PassengerEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IFlightMapper {

    FlightDTO entityToDto(FlightEntity entity);

    FlightEntity dtoToEntity(FlightDTO dto);

    List<FlightDTO> listEntityToListDto(List<FlightEntity> entity);

    List<FlightEntity>listDtoToListEntity(List<FlightDTO> dto);

}
