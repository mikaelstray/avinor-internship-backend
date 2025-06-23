package no.avinor.gate_occupancy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class GateOccupancyApplication {

	public static void main(String[] args) {
		SpringApplication.run(GateOccupancyApplication.class, args);
}}
