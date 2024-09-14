package com.example.demo.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@AllArgsConstructor
@Entity
@Table(name = "room")
public class Room {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "number")
    private String number;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Double price;
    
    @Column(name = "capacity")
    private Integer capacity;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn (name = "hotel_id")
    private Hotel hotel;
    
    @OneToMany(mappedBy = "room")
    private List<Reservation> reservations;

    public Room() {
    }
}
