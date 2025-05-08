package com.me.sensor.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.me.sensor.models.RickandmortyCharacter;
import com.me.sensor.services.RickandmortyService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class RickAndMortyController {

    //private final RestTemplate restTemplate;
    private final RickandmortyService rickandmortyService;
   

    @GetMapping("/random")
    public String getRandomCharacter(Model model) {
        RickandmortyCharacter character = rickandmortyService.getCharacterRandom();
        model.addAttribute("character", character);
        return "characterCard";
    }
}
