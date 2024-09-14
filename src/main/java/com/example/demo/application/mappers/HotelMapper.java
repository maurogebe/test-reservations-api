package com.example.demo.application.mappers;

import com.example.demo.application.dtos.HotelDTO;
import com.example.demo.domain.entities.Hotel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HotelMapper {

    HotelMapper INSTANCE = Mappers.getMapper( HotelMapper.class );

    HotelDTO hotelToHotelDTO(Hotel hotel);

    Hotel hotelDTOToHotel(HotelDTO hotelDTO);
    
    List<HotelDTO> hotelsToHotelsDTO(List<Hotel> hotels);
    
    List<Hotel> hotelsDTOToHotels(List<HotelDTO> hotelsDTO);

}
