package com.me.sensor.controllers;

import com.me.sensor.models.Superhero;
import com.me.sensor.services.BattleService;
import com.me.sensor.services.TeamGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class BattleViewController {

    @Autowired
    private TeamGeneratorService teamGeneratorService;

    @Autowired
    private BattleService battleService;

    @GetMapping("/battle-view")
    public String showBattle(Model model) {
        // Genera dos equipos aleatorios, cada uno con 3 héroes
        List<Superhero> teamA = teamGeneratorService.getRandomTeam(3);
        List<Superhero> teamB = teamGeneratorService.getRandomTeam(3);
        String winner = battleService.determineWinner(teamA, teamB);

        model.addAttribute("teamA", teamA);
        model.addAttribute("teamB", teamB);
        model.addAttribute("winner", winner);

        return "battle"; // Esto buscará el template battle.html
    }
}
