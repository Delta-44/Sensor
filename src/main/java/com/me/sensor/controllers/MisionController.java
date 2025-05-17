package com.me.sensor.controllers;

import com.me.sensor.models.Mision;
import com.me.sensor.models.Automata;
import com.me.sensor.repositories.MisionRepository;
import com.me.sensor.repositories.AutomataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.List;
import java.util.ArrayList;

@RestController
@RequestMapping("/misiones")
public class MisionController {

    @Autowired
    private MisionRepository misionRepository;

    @Autowired
    private AutomataRepository automataRepository;

    // POST /misiones → Crear misión
    @PostMapping
    public ResponseEntity<Mision> crearMision(@RequestBody Mision mision) {
        Mision saved = misionRepository.save(mision);
        return ResponseEntity.ok(saved);
    }

    // GET /misiones → Listar todas las misiones
    @GetMapping
    public List<Mision> obtenerTodas() {
        return misionRepository.findAll();
    }

    // PATCH /misiones/{id}/asignar-robot → Añadir un robot a la misión  
    // Se espera que se pase el parámetro "robotId" por query.
    @PatchMapping("/{id}/asignar-robot")
    public ResponseEntity<String> asignarRobot(@PathVariable("id") String misionId,
                                               @RequestParam("robotId") String robotId) {
        Optional<Mision> optionalMision = misionRepository.findById(misionId);
        if (optionalMision.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Optional<Automata> optionalAutomata = automataRepository.findById(robotId);
        if (optionalAutomata.isEmpty()) {
            return ResponseEntity.badRequest().body("Robot no encontrado");
        }
        Mision mision = optionalMision.get();
        Automata automata = optionalAutomata.get();
        
        // Opcional: Comprobar si el robot tiene energía y nivel suficientes
        if (automata.getEnergiaActual() <= 0 || automata.getNivel() <= 0) {
            return ResponseEntity.badRequest().body("Robot con energía o nivel insuficiente");
        }
        
        // Asignar el robot a la misión
        List<String> robotsParticipantes = mision.getRobotsParticipantes();
        if (robotsParticipantes == null) {
            robotsParticipantes = new ArrayList<>();
        }
        if (!robotsParticipantes.contains(robotId)) {
            robotsParticipantes.add(robotId);
        }
        mision.setRobotsParticipantes(robotsParticipantes);
        misionRepository.save(mision);
        
        // Agregar la misión al historial del robot
        List<String> misiones = automata.getMisionesRealizadas();
        if (misiones == null) {
            misiones = new ArrayList<>();
        }
        if (!misiones.contains(misionId)) {
            misiones.add(misionId);
        }
        automata.setMisionesRealizadas(misiones);
        automataRepository.save(automata);
        
        return ResponseEntity.ok("Robot asignado a la misión exitosamente");
    }
    
    // Opcional: Filtrar misiones por dificultad o resultado
    @GetMapping("/filtro")
    public List<Mision> filtrar(@RequestParam(value="dificultad", required=false) String dificultad,
                                @RequestParam(value="resultado", required=false) String resultado) {
        if (dificultad != null) {
            return misionRepository.findByDificultad(dificultad);
        } else if (resultado != null) {
            return misionRepository.findByResultado(resultado);
        } else {
            return misionRepository.findAll();
        }
    }
}
