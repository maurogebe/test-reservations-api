package com.example.demo.application.usecases;

import com.example.demo.application.dtos.CityDTO;
import com.example.demo.application.exeptions.ApiRequestException;
import com.example.demo.application.mappers.CityMapper;
import com.example.demo.domain.entities.City;
import com.example.demo.domain.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityUseCase {

    private final CityRepository cityRepository;

    @Autowired
    public CityUseCase(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public CityDTO getCityById(Long id) {
        Optional<City> cityById = this.cityRepository.findById(id);

        if(cityById.isEmpty()) throw new ApiRequestException("No se encontró la ciudad con ID: " + id, HttpStatus.NOT_FOUND);

        return CityMapper.INSTANCE.cityToCityDTO(cityById.get());
    }
    
    public List<CityDTO> getCities() {
        List<City> cities = this.cityRepository.findAll();
        return CityMapper.INSTANCE.citiesToCitiesDTO(cities);
    }

    public void createCity(CityDTO city) {
        this.cityRepository.save(CityMapper.INSTANCE.cityDTOToCity(city));
    }
}
