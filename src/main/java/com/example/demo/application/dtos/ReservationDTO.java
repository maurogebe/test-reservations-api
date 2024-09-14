package com.example.demo.application.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDTO {
    
    @JsonProperty("startDate")
    private LocalDateTime startDate;
    
    @JsonProperty("endDate")
    private LocalDateTime endDate;
    
    @JsonProperty("room")
    private RoomDTO room;
    
    @JsonProperty("customer")
    private CustomerDTO customer;

}
