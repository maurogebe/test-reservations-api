package com.example.demo.infrastructure.controllers;

import com.example.demo.application.dtos.CityDTO;
import com.example.demo.application.usecases.CityUseCase;
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

import java.util.List;

@Tag(name = "Ciudades", description = "Controlador para gestionar las operaciones relacionadas con las ciudades")
@Validated
@RestController
@RequestMapping("/city")
public class CityController {
    
    private final CityUseCase cityUsecase;
    
    @Autowired
    public CityController(CityUseCase cityUsecase) {
        this.cityUsecase = cityUsecase;
    }
    
    @Operation(summary = "Obtener una ciudad por su ID", description = "Permite consultar los detalles de una ciudad específica utilizando su identificador único (ID).")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Ciudad encontrada y retornada con éxito."),
        @ApiResponse(responseCode = "404", description = "No se encontró ninguna ciudad con el ID proporcionado."),
        @ApiResponse(responseCode = "500", description = "Ocurrió un error interno al procesar la solicitud.")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CityDTO> getCityById(@PathVariable Long id) {
        CityDTO city = this.cityUsecase.getCityById(id);
        return ResponseEntity.ok(city);
    }
    
    @Operation(summary = "Listar todas las ciudades", description = "Devuelve una lista completa de todas las ciudades registradas en el sistema.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de ciudades obtenida exitosamente."),
        @ApiResponse(responseCode = "404", description = "No se encontraron ciudades en el sistema."),
        @ApiResponse(responseCode = "500", description = "Error interno al intentar obtener las ciudades.")
    })
    @GetMapping
    public ResponseEntity<List<CityDTO>> getCities() {
        List<CityDTO> cities = this.cityUsecase.getCities();
        return ResponseEntity.ok(cities);
    }
    
    @Operation(summary = "Crear una nueva ciudad", description = "Permite crear una nueva ciudad proporcionando los datos necesarios.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Ciudad creada exitosamente."),
        @ApiResponse(responseCode = "400", description = "Datos inválidos o faltantes en la solicitud."),
        @ApiResponse(responseCode = "401", description = "No autorizado para realizar esta acción."),
        @ApiResponse(responseCode = "500", description = "Error interno al intentar crear la ciudad.")
    })
    @PostMapping
    public ResponseEntity<String> createCity(@Valid @RequestBody CityDTO city) {
        this.cityUsecase.createCity(city);
        return ResponseEntity.status(HttpStatus.CREATED).body("La ciudad ha sido creada exitosamente.");
    }
}
