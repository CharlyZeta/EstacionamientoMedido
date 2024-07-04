package net.bmmv.parking;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.time.LocalDate;
import java.time.LocalTime;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "API de Estacionamiento Medido de la ciudad de Santa Fe - Parking Santa Fe",
				version = "0.0.1",
				description = "Esta es una API generada como trabajo práctico de la materia DAOS 2024",
				contact = @Contact(
						name = "Juan José Benz - Gerardo Maidana",
						email = "jbenz@frsf.utn.edu.ar - gmmaidana@frsf.utn.edu.ar"
				),
				license = @License(
						name = "Licencia MIT",
						url = "https://opensource.org/licenses/MIT"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Json file collection para Postman",
				url = "https://drive.google.com/file/d/1W_1gfTD5KL5SKELBvDGIiYrkH6bhSAPH/view?usp=sharing"
		)

)


public class ParkingApplication {

	private static final Logger logger = LoggerFactory.getLogger(ParkingApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(ParkingApplication.class, args);
		{
			System.out.println("\n");
		logger.info("Iniciando aplicación Parking Santa Fe, " + LocalDate.now() + " a las " + LocalTime.now() );
			System.out.println("Puede visitar la documentación del sistema en http://localhost:8080/doc/swagger-ui/index.html#/\n");
			System.out.println("Puede descargar el Json Collection de Postman de https://drive.google.com/file/d/1W_1gfTD5KL5SKELBvDGIiYrkH6bhSAPH/view?usp=sharing\n");
		}
	}
}
