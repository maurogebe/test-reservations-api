package com.example.demo.infrastructure.controllers;

import com.example.demo.application.dtos.ReservationDTO;
import com.example.demo.application.dtos.RoomDTO;
import com.example.demo.application.usecases.RoomUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Tag(name = "Habitación", description = "Operaciones relacionadas con las habitaciones")
@Validated
@RestController
@RequestMapping("/room")
public class RoomController {

    private final RoomUseCase roomUsecase;

    @Autowired
    public RoomController(RoomUseCase roomUsecase) {
        this.roomUsecase = roomUsecase;
    }

    @Operation(summary = "Obtener la información de una habitación por ID", description = "Retorna la información de una habitación dado su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Operación exitosa"),
        @ApiResponse(responseCode = "404", description = "Habitación no encontrada"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/{id}")
    public ResponseEntity<RoomDTO> getRoomById(@PathVariable Long id) {
        RoomDTO room = this.roomUsecase.getRoomById(id);
        return ResponseEntity.ok(room);
    }
    
    @Operation(summary = "Obtener la información de las habitaciones filtradas", description = "Retorna la información de las habitaciones basadas en filtros opcionales como capacidad, fecha y ciudad")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Operación exitosa"),
        @ApiResponse(responseCode = "404", description = "Habitaciones no encontradas"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping
    public ResponseEntity<List<RoomDTO>> getRooms(@RequestParam(value = "cityId", required = false) Long cityId, @RequestParam(value = "startDate", required = false) LocalDateTime startDate, @RequestParam(value = "endDate", required = false) LocalDateTime endDate, @RequestParam(value = "capacity", required = false) Integer capacity) {
        List<RoomDTO> rooms = roomUsecase.getRooms(cityId, startDate, endDate, capacity);
        return ResponseEntity.ok(rooms);
    }
    
    @Operation(summary = "Crear una nueva habitación", description = "Crea una nueva habitación en el sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Habitación creada correctamente"),
        @ApiResponse(responseCode = "401", description = "No autorizado"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PostMapping
    public ResponseEntity<String> createRoom(@Valid @RequestBody RoomDTO room) {
        this.roomUsecase.createRoom(room);
        return ResponseEntity.status(HttpStatus.CREATED).body("Room created successfully.");
    }
}
