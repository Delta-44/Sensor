package com.me.sensor.services;
import com.me.sensor.models.WCharacter;
import com.me.sensor.repositories.CharacterRepository;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

import java.util.List;
import java.util.Random;

@Service
public class CharacterService {
    
    private final RestTemplate restTemplate;

    public CharacterService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public WCharacter getCharacterRandom(){
        String url = "https://rickandmortyapi.com/api/character/" + (int) (Math.random() * 826 + 1); // Hay 826 personajes en la API
        return restTemplate.getForObject(url, WCharacter.class);
    }

}