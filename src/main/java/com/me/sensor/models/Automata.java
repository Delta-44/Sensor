package com.me.sensor.models;

import lombok.Data;
import java.util.List;

@Data
public class Automata {
    private String id; // identificador único
    private String nombre;
    private String modelo;
    private String tipo; // asalto, defensa, espionaje, médico, etc.
    private int energiaActual;
    private int energiaMaxima;
    private int nivel; // experiencia o evolución
    private List<String> habilidades; // ej. "camuflaje", "ataque láser", "evisceracion", "bengala"
    private String estado; // "activo", "dañado", o "destruido"
    private List<String> misionesRealizadas; // lista de IDs de misiones completadas
}
