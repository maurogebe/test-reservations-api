package com.example.demo.application.usecases;

import com.example.demo.application.dtos.CountryDTO;
import com.example.demo.application.exeptions.ApiRequestException;
import com.example.demo.application.mappers.CountryMapper;
import com.example.demo.domain.entities.Country;
import com.example.demo.domain.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
}
