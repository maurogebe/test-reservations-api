package com.example.demo.application.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {
    
    @JsonProperty("id")
    private Long id;
    
    @NotBlank(message = "The firstname is required.")
    @JsonProperty("firstName")
    private String firstName;
    
    @NotBlank(message = "The lastname is required.")
    @JsonProperty("lastName")
    private String lastName;
    
    @Email(message = "Invalid email format.")
    @NotBlank(message = "The email is required.")
    @JsonProperty("email")
    private String email;

}
