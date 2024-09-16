package com.example.demo.application.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class CountryDTO {
    
    @JsonProperty("id")
    private Long id;
    
    @JsonProperty("code")
    private String code;
    
    @JsonProperty("name")
    private String name;

}
