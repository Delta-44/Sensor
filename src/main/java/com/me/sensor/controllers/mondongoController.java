package com.me.sensor.controllers;

import java.util.List;

import javax.swing.Spring;
import org.springframework.boot.SpringApplication;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.me.sensor.models.measures;
import com.me.sensor.utils.util;
import com.me.sensor.repositories.repo;

import lombok.NoArgsConstructor;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;




 @RestController
public class mondongoController {

    //http://localhost:8080/hola
   
@GetMapping("/hola")
    public String mondongo(){
        return "gilipollas";
    }
//http://localhost:8080/palindromo/anacardo
    @GetMapping("/palindromo/{word}")
        public String palindromo(@PathVariable String word){
String inverse = new StringBuilder(word).reverse().toString();
    return word.equalsIgnoreCase(inverse) ? "si es un palindromo" : "tu eres mongol";

        }

        //http://localhost:8080/pal?word=ana
        @GetMapping("/pal")
    public String pal(@RequestParam String word){
        
        return util.ispal(word) ? "si es un palindromo" : "tu eres mongol";
    }

    @PostMapping("/savedisk")
    public String postMethod(@RequestParam String user,
    @RequestParam String password){
        System.out.println("doby "+user+' '+password);
        return "mongol de los cojones";
    }


    private final repo Repo;

    public mondongoController(repo Repo) {
        this.Repo = Repo;
    }
    @PostMapping("/saveMeasure")
    @ResponseStatus(HttpStatus.CREATED)
    public void prueba(@RequestBody measures sensorData) {
        //TODO: process POST request
       Repo.save(sensorData);
        System.out.println("sensor data: " + sensorData.getTemperature() + " " + sensorData.getHumidity() );
   
    }

    @GetMapping("/getMeasure")
    public ResponseEntity<List<measures>> getAllSensorData() {
        List<measures> measuresList = Repo.findAll();
        return new ResponseEntity<>(measuresList, HttpStatus.OK);
    }
    @RestController
@RequestMapping("/test")
public class TestController {
    @GetMapping
    public ResponseEntity<String> prueba() {
        return ResponseEntity.ok("¡Servidor funcionando!");
    }
}
}

