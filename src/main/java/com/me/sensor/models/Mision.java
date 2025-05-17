package com.me.sensor.models;

import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class Mision {
    private String id; // identificador único
    private String nombre; // título de la misión
    private String descripcion; // en qué consiste la misión
    private String dificultad; // "baja", "media" o "alta"
    private int recompensa; // recursos o experiencia
    private List<String> robotsParticipantes; // lista de IDs de robots
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String resultado; // "pendiente", "éxito" o "fracaso"
}
