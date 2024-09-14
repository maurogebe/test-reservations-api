package com.example.demo.application.usecases;

import com.example.demo.application.dtos.HotelDTO;
import com.example.demo.application.exeptions.ApiRequestException;
import com.example.demo.application.mappers.HotelMapper;
import com.example.demo.domain.entities.Hotel;
import com.example.demo.domain.repositories.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelUseCase {

    private final HotelRepository hotelRepository;

    @Autowired
    public HotelUseCase(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public HotelDTO getHotelById(Long id) {
        Optional<Hotel> hotelById = this.hotelRepository.findById(id);

        if(hotelById.isEmpty()) throw new ApiRequestException("No se encontró el hotel con ID: " + id, HttpStatus.NOT_FOUND);

        return HotelMapper.INSTANCE.hotelToHotelDTO(hotelById.get());
    }
    
    public List<HotelDTO> getHotels() {
        List<Hotel> hotels = this.hotelRepository.findAll();
        return HotelMapper.INSTANCE.hotelsToHotelsDTO(hotels);
    }

    public void createHotel(HotelDTO hotel) {
        this.hotelRepository.save(HotelMapper.INSTANCE.hotelDTOToHotel(hotel));
    }
}
