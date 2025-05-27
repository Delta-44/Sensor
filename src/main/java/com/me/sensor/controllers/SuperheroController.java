package com.me.sensor.controllers;

import com.me.sensor.models.Superhero;
import com.me.sensor.services.SuperheroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/superheroes")
public class SuperheroController {

    @Autowired
    private SuperheroService superheroService;

    @GetMapping("/{id}")
    public ResponseEntity<Superhero> getSuperhero(@PathVariable String id) {
        try {
            Superhero hero = superheroService.getSuperheroById(id);
            return ResponseEntity.ok(hero);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    
    }

    
}
