package com.me.sensor.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@Data
public class Mision {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nombre;
    private String descripcion;
    
    // Dificultad: "baja", "media", "alta"
    private String dificultad;
    
    // Recompensa en puntos o monedas
    private int recompensa;

    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    
    // Resultado: "pendiente", "éxito", "fracaso"
    private String resultado;
}
