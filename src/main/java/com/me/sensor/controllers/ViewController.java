package com.me.sensor.controllers;

import com.me.sensor.repositories.AutomataRepository;
import com.me.sensor.repositories.MisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @Autowired
    private AutomataRepository automataRepository;

    @Autowired
    private MisionRepository misionRepository;

     @GetMapping("/vent")
    public String dashboard(Model model) {
        model.addAttribute("misiones", misionRepository.findAll());
        model.addAttribute("robotsAll", automataRepository.findAll());
        return "vent"; // Se buscará el archivo dashboard.html en resources/templates
    }

    @GetMapping("/view/robots")
    public String viewRobots(Model model) {
        model.addAttribute("robots", automataRepository.findAll());
        return "robots"; // Se espera que exista un archivo robots.html en resources/templates
    }

    @GetMapping("/view/misiones")
    public String viewMisiones(Model model) {
        model.addAttribute("misiones", misionRepository.findAll());
        // Agregamos todos los robots para la asignación
        model.addAttribute("robotsAll", automataRepository.findAll());
        return "misiones";
    }

   

}
