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
    
    @GetMapping("/view/robots")
    public String viewRobots(Model model) {
        model.addAttribute("robots", automataRepository.findAll());
        return "robots"; // Se espera que exista un archivo robots.html en resources/templates
    }
    
    @GetMapping("/view/misiones")
    public String viewMisiones(Model model) {
        model.addAttribute("misiones", misionRepository.findAll());
        return "misiones"; // Se espera que exista un archivo misiones.html en resources/templates
    }
}
