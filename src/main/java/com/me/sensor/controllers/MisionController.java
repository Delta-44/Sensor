package com.me.sensor.controllers;

import com.me.sensor.models.Automata;
import com.me.sensor.models.Mision;
import com.me.sensor.repositories.AutomataRepository;
import com.me.sensor.repositories.MisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/misiones")
public class MisionController {

    @Autowired
    private MisionRepository misionRepository;

    @Autowired
    private AutomataRepository automataRepository;

    // POST /misiones → crear misión (vía formulario)
    @PostMapping
    public String createMision(@ModelAttribute Mision mision) {
        if (mision.getResultado() == null) {
            mision.setResultado("pendiente");
        }
        misionRepository.save(mision);
        return "redirect:/view/misiones";
    }

    // GET /misiones → listar todas las misiones (API JSON)
    @GetMapping(produces="application/json")
    @ResponseBody
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

    // PATCH /misiones/{id}/asignar-robot → asignar un robot a la misión 
    // Se invoca desde un formulario que envía _method=PATCH.
  @PatchMapping("/{misionId}/asignar-robot")
    public String asignarRobot(
            @PathVariable("misionId") Long misionId,
            @RequestParam("robotId") Long robotId) {

        Optional<Mision> optMision = misionRepository.findById(misionId);
        if (optMision.isEmpty()) {
            // Redirige con mensaje de error o de aviso si no se encontró la misión
            return "redirect:/view/misiones?error=notFound";
        }
        Mision mision = optMision.get();

        Optional<Automata> optAutomata = automataRepository.findById(robotId);
        if (optAutomata.isEmpty()) {
            // Redirige en caso de no encontrar el robot
            return "redirect:/view/misiones?error=robotNotFound";
        }
        Automata robot = optAutomata.get();

        // Actualiza la misión añadiendo el robot.
        // (Aquí puedes incluir validaciones adicionales: por ejemplo, evitar duplicados)
        mision.getRobotsParticipantes().add(robot);

        misionRepository.save(mision);

        // Redirige nuevamente a la vista de misiones o muestra un mensaje de éxito
        return "redirect:/view/misiones";
    }

    
    // Método auxiliar para la validez según la dificultad de la misión
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
    
    // DTO para recibir el ID del robot (vía formulario)
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
