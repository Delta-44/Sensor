package com.me.sensor.controllers;

import com.me.sensor.models.Automata;
import com.me.sensor.models.Mision;
import com.me.sensor.repositories.AutomataRepository;
import com.me.sensor.repositories.MisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class DashboardController {

    @Autowired
    private AutomataRepository automataRepository;

    @Autowired
    private MisionRepository misionRepository;

    @GetMapping("/dashboard")
    public String verDashboard(Model model) {
        // Obtenemos todos los robots
        List<Automata> robots = automataRepository.findAll();
        // Obtenemos todas las misiones (alternativamente, podrías cargar solo las que necesites)
        List<Mision> misiones = misionRepository.findAll();

        model.addAttribute("robots", robots);
        model.addAttribute("misiones", misiones);

        return "dashboard"; // Se usará el template dashboard.html
    }
}

