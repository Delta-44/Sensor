package com.me.sensor.services;

import com.me.sensor.models.Superhero;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BattleService {
    public String determineWinner(List<Superhero> teamA, List<Superhero> teamB) {
        int teamAPower = teamA.stream().mapToInt(hero -> {
            try {
                return Integer.parseInt(hero.getPowerstats().getOrDefault("power", "0"));
            } catch (NumberFormatException e) {
                return 0;
            }
        }).sum();

        int teamBPower = teamB.stream().mapToInt(hero -> {
            try {
                return Integer.parseInt(hero.getPowerstats().getOrDefault("power", "0"));
            } catch (NumberFormatException e) {
                return 0;
            }
        }).sum();

        if (teamAPower > teamBPower) {
            return "¡Equipo A gana: " + teamAPower + " vs " + teamBPower + "!";
        } else if (teamBPower > teamAPower) {
            return "¡Equipo B gana: " + teamBPower + " vs " + teamAPower + "!";
        } else {
            return "Empate: ambos equipos tienen " + teamAPower + " puntos.";
        }
    }
}
