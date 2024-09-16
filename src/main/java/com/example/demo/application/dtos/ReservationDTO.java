package com.example.demo.application.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDTO {
    
    @JsonProperty("id")
    private Long id;
    
    @NotBlank(message = "The start date is required.")
    @JsonProperty("startDate")
    private LocalDateTime startDate;
    
    @NotBlank(message = "The end date is required.")
    @JsonProperty("endDate")
    private LocalDateTime endDate;
    
    @NotBlank(message = "The room is required.")
    @JsonProperty("room")
    private RoomDTO room;
    
    @NotBlank(message = "The customer is required.")
    @JsonProperty("customer")
    private CustomerDTO customer;

}
