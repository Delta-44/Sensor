package com.me.sensor.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.me.sensor.models.Asignacion;
import com.me.sensor.models.Automata;
import com.me.sensor.models.Mision;
import com.me.sensor.repositories.AsignacionRepository;
import com.me.sensor.repositories.AutomataRepository;
import com.me.sensor.repositories.MisionRepository;

import lombok.Data;
import lombok.NoArgsConstructor;

@Controller
@RequestMapping("/asignaciones")
public class AsignacionController {

    @Autowired
    private AsignacionRepository asignacionRepository;
    
    @Autowired
    private MisionRepository misionRepository;
    
    @Autowired
    private AutomataRepository automataRepository;
    
    // DTO para recibir los parámetros del formulario
    public static class AsignacionDTO {
        private Long misionId;
        private Long robotId;

        public Long getMisionId() {
            return misionId;
        }
        public void setMisionId(Long misionId) {
            this.misionId = misionId;
        }
        public Long getRobotId() {
            return robotId;
        }
        public void setRobotId(Long robotId) {
            this.robotId = robotId;
        }
    }
    
    // GET /asignaciones/crear → muestra el formulario para crear una asignación.
    @GetMapping("/crear")
    public String crearAsignacionForm(Model model) {
        model.addAttribute("misiones", misionRepository.findAll());
        model.addAttribute("robots", automataRepository.findAll());
        model.addAttribute("asignacionDTO", new AsignacionDTO());
        return "crear_asignacion";  // Nombre del template Thymeleaf
    }
    
    // POST /asignaciones → procesa el formulario y crea la asignación.
    @PostMapping
    public String createAsignacion(@ModelAttribute("asignacionDTO") AsignacionDTO dto) {
        Optional<Mision> optMision = misionRepository.findById(dto.getMisionId());
        Optional<Automata> optAutomata = automataRepository.findById(dto.getRobotId());
        
        if (optMision.isEmpty() || optAutomata.isEmpty()){
            // Podrías agregar mensajes de error, por ahora redirigimos con un parámetro error.
            return "redirect:/asignaciones/crear?error";
        }
        
        Asignacion asignacion = new Asignacion();
        asignacion.setMision(optMision.get());
        asignacion.setAutomata(optAutomata.get());
        
        asignacionRepository.save(asignacion);
        
        return "redirect:/asignaciones/view";
    }
    
    // GET /asignaciones/view → muestra la lista de asignaciones.
    @GetMapping("/view")
    public String viewAsignaciones(Model model) {
        model.addAttribute("asignaciones", asignacionRepository.findAll());
        return "asignaciones";  // Nombre del template Thymeleaf para listar asignaciones.
    }
}
