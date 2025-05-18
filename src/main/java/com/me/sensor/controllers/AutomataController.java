package com.me.sensor.controllers;

import com.me.sensor.models.Automata;
import com.me.sensor.models.Mision;
import com.me.sensor.repositories.AutomataRepository;
import com.me.sensor.repositories.MisionRepository;
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
    
    @Autowired
    private MisionRepository misionRepository;

    // POST /robots → crear robot
    @PostMapping
    public Automata createAutomata(@RequestBody Automata automata) {
        return automataRepository.save(automata);
    }

    // GET /robots → listar todos los robots
    @GetMapping
    public List<Automata> getAllAutomatas() {
        return automataRepository.findAll();
    }

    // GET /robots/{id}/misiones → mostrar historial de misiones del robot
    @GetMapping("/{id}/misiones")
    public ResponseEntity<?> getRobotMisiones(@PathVariable Long id) {
        Optional<Automata> automataOpt = automataRepository.findById(id);
        if(automataOpt.isPresent()){
            Automata automata = automataOpt.get();
            return ResponseEntity.ok(automata.getMisionesRealizadas());
        }
        return ResponseEntity.notFound().build();
    }
    
    // PATCH /robots/{id}/recargar → restablecer energía al máximo
    @PatchMapping("/{id}/recargar")
    public ResponseEntity<?> recargarRobot(@PathVariable Long id) {
        Optional<Automata> automataOpt = automataRepository.findById(id);
        if(automataOpt.isPresent()){
            Automata automata = automataOpt.get();
            automata.setEnergiaActual(automata.getEnergiaMaxima());
            automataRepository.save(automata);
            return ResponseEntity.ok(automata);
        }
        return ResponseEntity.notFound().build();
    }

    // PATCH /robots/{id}/subir-nivel → aumentar el nivel del robot
    @PatchMapping("/{id}/subir-nivel")
    public ResponseEntity<?> subirNivel(@PathVariable Long id) {
        Optional<Automata> automataOpt = automataRepository.findById(id);
        if(automataOpt.isPresent()){
            Automata automata = automataOpt.get();
            automata.setNivel(automata.getNivel() + 1);
            automataRepository.save(automata);
            return ResponseEntity.ok(automata);
        }
        return ResponseEntity.notFound().build();
    }
}
