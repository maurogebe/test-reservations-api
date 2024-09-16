package com.example.demo.application.usecases;

import com.example.demo.application.dtos.CountryDTO;
import com.example.demo.application.exeptions.ApiRequestException;
import com.example.demo.application.mappers.CountryMapper;
import com.example.demo.domain.entities.City;
import com.example.demo.domain.entities.Country;
import com.example.demo.domain.entities.State;
import com.example.demo.domain.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.*;

@Service
public class CountryUseCase {

    private final CountryRepository countryRepository;

    @Autowired
    public CountryUseCase(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public CountryDTO getCountryById(Long id) {
        Optional<Country> countryById = this.countryRepository.findById(id);

        if(countryById.isEmpty()) throw new ApiRequestException("No se encontró el país con ID: " + id, HttpStatus.NOT_FOUND);

        return CountryMapper.INSTANCE.countryToCountryDTO(countryById.get());
    }

    public void createCountry(CountryDTO country) {
        Optional<Country> countryByCode = this.countryRepository.findByCode(country.getCode());
        if(countryByCode.isPresent()) throw new ApiRequestException("Ya existe un país con este codigo: " + country.getCode(), HttpStatus.BAD_REQUEST);
        this.countryRepository.save(CountryMapper.INSTANCE.countryDTOToCountry(country));
    }
    
    public void seedCountriesWithStateAndCities() {
        // Crear el país Colombia
        Country colombia = new Country();
        colombia.setCode("CO");
        colombia.setName("Colombia");
        
        // Crear los estados y ciudades para Colombia
        State antioquia = new State();
        antioquia.setName("Antioquia");
        antioquia.setCountry(colombia);
        antioquia.setCities(Set.of(
            new City("Medellín", antioquia),
            new City("Envigado", antioquia),
            new City("Bello", antioquia)
        ));
        
        State cundinamarca = new State();
        cundinamarca.setName("Cundinamarca");
        cundinamarca.setCountry(colombia);
        cundinamarca.setCities(Set.of(
            new City("Bogotá", cundinamarca),
            new City("Soacha", cundinamarca),
            new City("Chía", cundinamarca)
        ));
        
        State valleDelCauca = new State();
        valleDelCauca.setName("Valle del Cauca");
        valleDelCauca.setCountry(colombia);
        valleDelCauca.setCities(Set.of(
            new City("Cali", valleDelCauca),
            new City("Palmira", valleDelCauca),
            new City("Buenaventura", valleDelCauca)
        ));
        
        // Asignar los estados al país
        colombia.setStates(Set.of(antioquia, cundinamarca, valleDelCauca));
        
        // Verificar si el país ya existe antes de guardarlo
        Optional<Country> countryByCode = this.countryRepository.findByCode(colombia.getCode());
        if (countryByCode.isPresent()) {
            throw new ApiRequestException("Ya existe un país con este código: " + colombia.getCode(), HttpStatus.BAD_REQUEST);
        }
        
        // Guardar el país en la base de datos
        this.countryRepository.save(colombia);
    }
    
    
    
    
}
