package com.example.demo.application.mappers;

import com.example.demo.application.dtos.RoomDTO;
import com.example.demo.domain.entities.Room;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoomMapper {

    RoomMapper INSTANCE = Mappers.getMapper( RoomMapper.class );

    RoomDTO roomToRoomDTO(Room room);

    Room roomDTOToRoom(RoomDTO roomDTO);
    
    List<RoomDTO> roomsToRoomsDTO(List<Room> rooms);
    
    List<Room> roomsDTOToRooms(List<RoomDTO> roomsDTO);

}
