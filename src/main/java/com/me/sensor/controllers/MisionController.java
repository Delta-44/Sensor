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
@RequestMapping("/misiones")
public class MisionController {

    @Autowired
    private MisionRepository misionRepository;

    @Autowired
    private AutomataRepository automataRepository;

    // POST /misiones → crear misión
    @PostMapping
    public Mision createMision(@RequestBody Mision mision) {
        if (mision.getResultado() == null) {
            mision.setResultado("pendiente");
        }
        return misionRepository.save(mision);
    }

    // GET /misiones → listar todas las misiones (opcionalmente filtrando por dificultad o resultado)
    @GetMapping
    public List<Mision> getAllMisiones(@RequestParam(required = false) String dificultad,
                                       @RequestParam(required = false) String resultado) {
        if (dificultad != null) {
            return misionRepository.findByDificultad(dificultad);
        } else if (resultado != null) {
            return misionRepository.findByResultado(resultado);
        } else {
            return misionRepository.findAll();
        }
    }

    // PATCH /misiones/{id}/asignar-robot → añadir un robot a la misión
    @PatchMapping("/{id}/asignar-robot")
    public ResponseEntity<?> asignarRobot(@PathVariable Long id, @RequestBody AsignarRobotDTO dto) {
        Optional<Mision> misionOpt = misionRepository.findById(id);
        if (misionOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Mision mision = misionOpt.get();

        Optional<Automata> automataOpt = automataRepository.findById(dto.getRobotId());
        if (automataOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Robot no encontrado con ID: " + dto.getRobotId());
        }
        Automata robot = automataOpt.get();

        // Comprobar que el robot tenga la energía y nivel suficientes para la misión
        if (!puedeParticipar(mision, robot)) {
            return ResponseEntity.badRequest().body("El robot no cumple los requisitos para participar en esta misión (energía o nivel insuficiente).");
        }

        // Asignar el robot a la misión
        mision.getRobotsParticipantes().add(robot);
        robot.getMisionesRealizadas().add(mision);

        misionRepository.save(mision);
        automataRepository.save(robot);
        return ResponseEntity.ok(mision);
    }
    
    // Método auxiliar para comprobar requisitos del robot según la dificultad de la misión
    private boolean puedeParticipar(Mision mision, Automata robot) {
        String dificultad = mision.getDificultad().toLowerCase();
        switch (dificultad) {
            case "alta":
                return robot.getNivel() >= 3 && robot.getEnergiaActual() >= robot.getEnergiaMaxima() * 0.9;
            case "media":
                return robot.getNivel() >= 2 && robot.getEnergiaActual() >= robot.getEnergiaMaxima() * 0.75;
            default:
                // Para misión "baja" o cualquier otro valor
                return robot.getNivel() >= 1 && robot.getEnergiaActual() >= robot.getEnergiaMaxima() * 0.5;
        }
    }
    
    // DTO para recibir el ID del robot a asignar
    public static class AsignarRobotDTO {
        private Long robotId;

        public Long getRobotId() {
            return robotId;
        }

        public void setRobotId(Long robotId) {
            this.robotId = robotId;
        }
    }
}
