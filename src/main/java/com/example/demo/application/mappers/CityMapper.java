package com.example.demo.application.mappers;

import com.example.demo.application.dtos.CityDTO;
import com.example.demo.domain.entities.City;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CityMapper {

    CityMapper INSTANCE = Mappers.getMapper( CityMapper.class );

    CityDTO cityToCityDTO(City city);

    City cityDTOToCity(CityDTO cityDTO);
    
    List<CityDTO> citiesToCitiesDTO(List<City> cities);
    
    List<City> citiesDTOToCities(List<CityDTO> citiesDTO);

}
