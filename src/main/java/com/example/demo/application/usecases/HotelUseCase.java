package com.example.demo.application.usecases;

import com.example.demo.application.dtos.HotelDTO;
import com.example.demo.application.exeptions.ApiRequestException;
import com.example.demo.application.mappers.HotelMapper;
import com.example.demo.domain.entities.City;
import com.example.demo.domain.entities.Hotel;
import com.example.demo.domain.entities.Room;
import com.example.demo.domain.repositories.CityRepository;
import com.example.demo.domain.repositories.HotelRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class HotelUseCase {
    
    private final HotelRepository hotelRepository;
    private final CityRepository cityRepository;

    @Autowired
    public HotelUseCase(HotelRepository hotelRepository, CityRepository cityRepository) {
        this.hotelRepository = hotelRepository;
        this.cityRepository = cityRepository;
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
    
    public void seedHotelsAndRooms() {
        // Encontrar las ciudades a las que asignaremos los hoteles
        Optional<City> medellinOpt = cityRepository.findByName("Medellín");
        Optional<City> bogotaOpt = cityRepository.findByName("Bogotá");
        
        if (medellinOpt.isEmpty() || bogotaOpt.isEmpty()) {
            throw new ApiRequestException("Las ciudades Medellín o Bogotá no se encuentran en la base de datos", HttpStatus.BAD_REQUEST);
        }
        
        City medellin = medellinOpt.get();
        City bogota = bogotaOpt.get();
        
        // Crear un hotel en Medellín
        Hotel hotelMedellin = new Hotel();
        hotelMedellin.setName("Hotel Medellín Plaza");
        hotelMedellin.setDescription("Hotel de lujo en el centro de Medellín");
        hotelMedellin.setAddress("Calle 50 #70-30");
        hotelMedellin.setCity(medellin);
        
        // Agregar habitaciones al hotel de Medellín
        Room roomMedellin1 = new Room();
        roomMedellin1.setNumber("201");
        roomMedellin1.setDescription("Habitación sencilla");
        roomMedellin1.setPrice(120.0);
        roomMedellin1.setCapacity(2);
        roomMedellin1.setHotel(hotelMedellin);
        
        Room roomMedellin2 = new Room();
        roomMedellin2.setNumber("202");
        roomMedellin2.setDescription("Habitación doble");
        roomMedellin2.setPrice(180.0);
        roomMedellin2.setCapacity(4);
        roomMedellin2.setHotel(hotelMedellin);
        
        hotelMedellin.setRooms(List.of(roomMedellin1, roomMedellin2));
        
        // Crear un hotel en Bogotá
        Hotel hotelBogota = new Hotel();
        hotelBogota.setName("Hotel Bogotá Capital");
        hotelBogota.setDescription("Hotel moderno cerca del aeropuerto en Bogotá");
        hotelBogota.setAddress("Avenida El Dorado #100-89");
        hotelBogota.setCity(bogota);
        
        // Agregar habitaciones al hotel de Bogotá
        Room roomBogota1 = new Room();
        roomBogota1.setNumber("301");
        roomBogota1.setDescription("Suite ejecutiva");
        roomBogota1.setPrice(300.0);
        roomBogota1.setCapacity(4);
        roomBogota1.setHotel(hotelBogota);
        
        Room roomBogota2 = new Room();
        roomBogota2.setNumber("302");
        roomBogota2.setDescription("Habitación estándar");
        roomBogota2.setPrice(150.0);
        roomBogota2.setCapacity(2);
        roomBogota2.setHotel(hotelBogota);
        
        hotelBogota.setRooms(List.of(roomBogota1, roomBogota2));
        
        // Guardar los hoteles en la base de datos
        this.hotelRepository.save(hotelMedellin);
        this.hotelRepository.save(hotelBogota);
    }
    
    
}
