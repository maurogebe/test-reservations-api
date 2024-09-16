package com.example.demo.infrastructure.controllers;

import com.example.demo.application.usecases.CountryUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Paises", description = "Controlador para gestionar las operaciones relacionadas con los paises.")
@Validated
@RestController
@RequestMapping("/country")
public class CountryController {
    
    private final CountryUseCase countryUsecase;
    
    @Autowired
    public CountryController(CountryUseCase countryUsecase) {
        this.countryUsecase = countryUsecase;
    }
    
    @Operation(summary = "Crear datos iniciales de paises con estados y ciudades", description = "Permite crear datos iniciales en base a unos json.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Paises creados exitosamente."),
        @ApiResponse(responseCode = "500", description = "Error interno al intentar crear los paises.")
    })
    @GetMapping("/seed")
    public ResponseEntity<String> seedCountries() {
        this.countryUsecase.seedCountriesWithStateAndCities();
        return ResponseEntity.status(HttpStatus.CREATED).body("Los paises han sido creados exitosamente.");
    }
}
