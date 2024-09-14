package com.example.demo.application.mappers;

import com.example.demo.application.dtos.ReservationDTO;
import com.example.demo.domain.entities.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReservationMapper {

    ReservationMapper INSTANCE = Mappers.getMapper( ReservationMapper.class );

    ReservationDTO reservationToReservationDTO(Reservation reservation);

    Reservation reservationDTOToReservation(ReservationDTO reservationDTO);
    
    List<ReservationDTO> reservationsToReservationsDTO(List<Reservation> reservations);
    
    List<Reservation> reservationsDTOToReservations(List<ReservationDTO> reservationsDTO);

}
