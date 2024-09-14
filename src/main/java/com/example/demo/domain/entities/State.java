package com.example.demo.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter @Setter
@AllArgsConstructor
@Entity
@Table(name = "state")
public class State {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn (name = "country_id")
    private Country country;
    
    @OneToMany(mappedBy = "state", cascade = CascadeType.ALL)
    private Set<City> cities;

    public State() {
    }
}
