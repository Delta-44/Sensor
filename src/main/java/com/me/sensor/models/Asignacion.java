package com.me.sensor.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Asignacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    // La misión a la que se asigna el robot
    @ManyToOne
    @JoinColumn(name = "mision_id", nullable = false)
    private Mision mision;
    
    // El robot asignado
    @ManyToOne
    @JoinColumn(name = "automata_id", nullable = false)
    private Automata automata;
}
