package com.example.demo.application.mappers;

import com.example.demo.application.dtos.CountryDTO;
import com.example.demo.domain.entities.Country;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CountryMapper {

    CountryMapper INSTANCE = Mappers.getMapper( CountryMapper.class );

    CountryDTO countryToCountryDTO(Country country);

    Country countryDTOToCountry(CountryDTO countryDTO);
    
    List<CountryDTO> countriesToCountriesDTO(List<Country> countries);
    
    List<Country> countriesDTOToCountries(List<CountryDTO> countriesDTO);

}
