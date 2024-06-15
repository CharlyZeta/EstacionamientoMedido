package net.bmmv.parking;

import net.bmmv.parking.dataFaker.usuariosFaker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ParkingApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParkingApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner() {
		return args -> {
			usuariosFaker users = new usuariosFaker();
			usuariosFaker.generateUsuarios(10);
			System.out.println("Generando usuarios en memoria...");

		};
	}
}
