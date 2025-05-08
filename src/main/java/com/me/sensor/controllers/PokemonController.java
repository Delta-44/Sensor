package com.me.sensor.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.me.sensor.models.PokemonCharacter;
import com.me.sensor.services.PokemonService;

import org.springframework.ui.Model;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PokemonController {

    private final PokemonService pokemonService;


    @GetMapping("/pokemonweb")
    public String randomPokemonPage(Model model) {
        PokemonCharacter character = pokemonService.getCharacterRandom();
        model.addAttribute("character", character);
        return "pokemon-card";
    }
}

