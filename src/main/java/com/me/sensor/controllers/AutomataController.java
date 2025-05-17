package com.me.sensor.controllers;

import com.me.sensor.models.Automata;
import com.me.sensor.repositories.AutomataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/robots")
public class AutomataController {

    @Autowired
    private AutomataRepository automataRepository;

    // POST /robots → Crear robot
    @PostMapping
    public ResponseEntity<Automata> crearAutomata(@RequestBody Automata automata) {
        Automata saved = automataRepository.save(automata);
        return ResponseEntity.ok(saved);
    }

    // GET /robots → Listar todos los robots
    @GetMapping
    public List<Automata> obtenerTodos() {
        return automataRepository.findAll();
    }

    // PATCH /robots/{id}/recargar → Restablecer energía al máximo
    @PatchMapping("/{id}/recargar")
    public ResponseEntity<String> recargarEnergia(@PathVariable("id") String id) {
        Optional<Automata> optional = automataRepository.findById(id);
        if (optional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Automata automata = optional.get();
        automata.setEnergiaActual(automata.getEnergiaMaxima());
        automataRepository.save(automata);
        return ResponseEntity.ok("Energía recargada al máximo");
    }

    // PATCH /robots/{id}/subir-nivel → Aumentar el nivel del robot
    @PatchMapping("/{id}/subir-nivel")
    public ResponseEntity<String> subirNivel(@PathVariable("id") String id) {
        Optional<Automata> optional = automataRepository.findById(id);
        if (optional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Automata automata = optional.get();
        automata.setNivel(automata.getNivel() + 1);
        automataRepository.save(automata);
        return ResponseEntity.ok("Nivel incrementado");
    }
    
    // Opcional: Mostrar historial de misiones de un robot
    @GetMapping("/{id}/misiones")
    public ResponseEntity<List<String>> historialMisiones(@PathVariable("id") String id) {
        Optional<Automata> optional = automataRepository.findById(id);
        if (optional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(optional.get().getMisionesRealizadas());
    }
}
