package com.me.sensor.controllers;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.me.sensor.models.WCharacter;



@Controller
public class CharacterController {
    
    @Autowired
    private RestTemplate restTemplate;
    
    private static final String API_URL = "https://rickandmortyapi.com/api/character/";
    
    @GetMapping("/wcharacter/random")
    public String getRandomCharacter(Model model) {
        try {
            // Si deseas generar un número aleatorio, hazlo aquí
            int randomId = 1;
            WCharacter character = restTemplate.getForObject(API_URL + randomId, WCharacter.class);
            model.addAttribute("character", character);
            return "characterCard";
        } catch (ResourceAccessException ex) {
            model.addAttribute("error", "No se pudo acceder a la API externa.");
            return "error";
        }
    }
}



