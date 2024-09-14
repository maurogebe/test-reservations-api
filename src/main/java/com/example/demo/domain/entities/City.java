package com.example.demo.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter @Setter
@AllArgsConstructor
@Entity
@Table(name = "city")
public class City {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn (name = "state_id")
    private State state;
    
    @OneToMany(mappedBy = "city")
    private List<Hotel> hotels;

    public City() {
    }
}
