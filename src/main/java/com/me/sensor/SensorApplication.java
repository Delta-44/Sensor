package com.me.sensor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.HiddenHttpMethodFilter;



@EntityScan("com.me.sensor.models")
@SpringBootApplication
public class SensorApplication {

@Bean
public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
    return new HiddenHttpMethodFilter();
}

	public static void main(String[] args) {
		SpringApplication.run(SensorApplication.class, args);
	}

}
