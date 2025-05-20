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


import com.me.sensor.models.Mision;
import com.me.sensor.repositories.MisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/misiones")
public class MisionController {

    @Autowired
    private MisionRepository misionRepository;

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
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
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
}
