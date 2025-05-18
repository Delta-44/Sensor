package com.me.sensor.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@Data
public class Automata {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nombre;
    private String modelo;
    private String tipo;
    
    private double energiaActual;
    private double energiaMaxima;
    
    private int nivel;
    
    // Lista de habilidades (por ejemplo: "camuflaje", "ataque láser")
    @ElementCollection
    private List<String> habilidades;
    
    // Estado: "activo", "dañado", "destruido"
    private String estado;
    
    // Relación many-to-many con la entidad Mision
    @ManyToMany(mappedBy = "robotsParticipantes")
    private Set<Mision> misionesRealizadas = new HashSet<>();
}
