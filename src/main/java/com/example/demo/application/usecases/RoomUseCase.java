package com.example.demo.application.usecases;

import com.example.demo.application.dtos.RoomDTO;
import com.example.demo.application.exeptions.ApiRequestException;
import com.example.demo.application.mappers.RoomMapper;
import com.example.demo.domain.entities.Room;
import com.example.demo.domain.repositories.RoomRepository;
import com.example.demo.domain.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomUseCase {

    private final RoomRepository roomRepository;

    @Autowired
    public RoomUseCase(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public RoomDTO getRoomById(Long id) {
        Optional<Room> roomById = this.roomRepository.findById(id);

        if(roomById.isEmpty()) throw new ApiRequestException("No se encontró la habitación con ID: " + id, HttpStatus.NOT_FOUND);

        return RoomMapper.INSTANCE.roomToRoomDTO(roomById.get());
    }
    
    public List<RoomDTO> getRooms() {
        List<Room> rooms = this.roomRepository.findAll();
        return RoomMapper.INSTANCE.roomsToRoomsDTO(rooms);
    }

    public void createRoom(RoomDTO room) {
        this.roomRepository.save(RoomMapper.INSTANCE.roomDTOToRoom(room));
    }
}
