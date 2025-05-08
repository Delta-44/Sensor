package com.me.sensor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;



@EntityScan("com.me.sensor.models")
@SpringBootApplication
public class SensorApplication {


	public static void main(String[] args) {
		SpringApplication.run(SensorApplication.class, args);
	}

}
