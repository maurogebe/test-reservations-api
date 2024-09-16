package com.example.demo.infrastructure.controllers;

import com.example.demo.application.dtos.ReservationDTO;
import com.example.demo.application.usecases.ReservationUseCase;
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

@Tag(name = "Reservaciones", description = "Controlador para gestionar operaciones relacionadas con las reservaciones")
@Validated
@RestController
@RequestMapping("/reservation")
public class ReservationController {
    
    private final ReservationUseCase reservationUsecase;
    
    @Autowired
    public ReservationController(ReservationUseCase reservationUsecase) {
        this.reservationUsecase = reservationUsecase;
    }
    
    @Operation(summary = "Obtener una reservación por su ID", description = "Permite consultar los detalles de una reservación específica utilizando su identificador único (ID).")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Reservación encontrada y retornada con éxito."),
        @ApiResponse(responseCode = "404", description = "No se encontró ninguna reservación con el ID proporcionado."),
        @ApiResponse(responseCode = "500", description = "Ocurrió un error interno al procesar la solicitud.")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ReservationDTO> getReservationById(@PathVariable Long id) {
        ReservationDTO reservation = this.reservationUsecase.getReservationById(id);
        return ResponseEntity.ok(reservation);
    }
    
    @Operation(summary = "Obtener la información de las reservaciones filtradas", description = "Retorna la información de las reservaciones basadas en filtros opcionales como cliente, fecha y habitación")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Operación exitosa"),
        @ApiResponse(responseCode = "404", description = "Reservaciones no encontradas"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping
    public ResponseEntity<List<ReservationDTO>> getReservations(@RequestParam(value = "customerId", required = false) Long customerId, @RequestParam(value = "roomId", required = false) Long roomId, @RequestParam(value = "startDate", required = false) LocalDateTime startDate, @RequestParam(value = "endDate", required = false) LocalDateTime endDate) {
        List<ReservationDTO> reservations = this.reservationUsecase.getReservations(customerId, startDate, endDate, roomId);
        return ResponseEntity.ok(reservations);
    }
    
    @Operation(summary = "Crear una nueva reservación", description = "Permite crear una nueva reservación proporcionando los datos necesarios.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Reservación creada exitosamente."),
        @ApiResponse(responseCode = "400", description = "Datos inválidos o faltantes en la solicitud."),
        @ApiResponse(responseCode = "401", description = "No autorizado para realizar esta acción."),
        @ApiResponse(responseCode = "500", description = "Error interno al intentar crear la reservación.")
    })
    @PostMapping
    public ResponseEntity<String> createReservation(@RequestBody ReservationDTO reservation) {
        this.reservationUsecase.createReservation(reservation);
        return ResponseEntity.status(HttpStatus.CREATED).body("La reservación ha sido creada exitosamente.");
    }
    
    @Operation(summary = "Actualizar una reservación por id", description = "Permite actualizar una reservación por id proporcionando los datos necesarios.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Reservación actualizada exitosamente."),
        @ApiResponse(responseCode = "400", description = "Datos inválidos."),
        @ApiResponse(responseCode = "401", description = "No autorizado para realizar esta acción."),
        @ApiResponse(responseCode = "500", description = "Error interno al intentar actualizar la reservación.")
    })
    @PutMapping("/{id}")
    public ResponseEntity<String> updateReservation(@PathVariable Long id, @RequestBody ReservationDTO reservation) {
        this.reservationUsecase.updateReservation(id, reservation);
        return ResponseEntity.ok("La reservación ha sido actualizada exitosamente.");
    }
    
    @Operation(summary = "Eliminar una reservación por id", description = "Permite eliminar una reservación por id proporcionando los datos necesarios.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Reservación eliminada exitosamente."),
        @ApiResponse(responseCode = "400", description = "Datos inválidos."),
        @ApiResponse(responseCode = "401", description = "No autorizado para realizar esta acción."),
        @ApiResponse(responseCode = "404", description = "La reservación ya fue eliminada o no existe."),
        @ApiResponse(responseCode = "500", description = "Error interno al intentar actualizar la reservación.")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReservation(@PathVariable Long id) {
        this.reservationUsecase.deleteReservation(id);
        return ResponseEntity.ok("La reservación ha sido eliminada exitosamente.");
    }
}
