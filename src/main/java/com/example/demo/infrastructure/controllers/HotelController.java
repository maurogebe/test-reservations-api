package com.example.demo.infrastructure.controllers;

import com.example.demo.application.usecases.HotelUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Paises", description = "Controlador para gestionar las operaciones relacionadas con los paises.")
@Validated
@RestController
@RequestMapping("/hotel")
public class HotelController {
    
    private final HotelUseCase hotelUsecase;
    
    @Autowired
    public HotelController(HotelUseCase hotelUsecase) {
        this.hotelUsecase = hotelUsecase;
    }
    
    @Operation(summary = "Crear datos iniciales de hoteles con habitaciones", description = "Permite crear datos iniciales en base a unos json.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Hoteles creados exitosamente."),
        @ApiResponse(responseCode = "500", description = "Error interno al intentar crear los hoteles.")
    })
    @GetMapping("/seed")
    public ResponseEntity<String> seedHotels() {
        this.hotelUsecase.seedHotelsAndRooms();
        return ResponseEntity.status(HttpStatus.CREATED).body("Los hoteles han sido creados exitosamente.");
    }
}
