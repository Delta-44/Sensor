package com.me.sensor.services;

import com.me.sensor.models.Superhero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class TeamGeneratorService {

    @Autowired
    private SuperheroService superheroService;

    // Lista de IDs de héroes válidos (según la documentación y ejemplos)
    private final List<String> superheroIds = List.of("70", "26", "123", "247", "107", "344", "159", "387", "69", "263");

    public List<Superhero> getRandomTeam(int teamSize) {
        List<String> idsCopy = new ArrayList<>(superheroIds);
        Collections.shuffle(idsCopy);
        List<Superhero> team = new ArrayList<>();

        for (int i = 0; i < teamSize && i < idsCopy.size(); i++) {
            try {
                Superhero hero = superheroService.getSuperheroById(idsCopy.get(i));
                if (hero != null) {
                    team.add(hero);
                }
            } catch (Exception e) {
                e.printStackTrace(); // Puedes mejorar la gestión de excepciones aquí.
            }
        }
        return team;
    }
}
