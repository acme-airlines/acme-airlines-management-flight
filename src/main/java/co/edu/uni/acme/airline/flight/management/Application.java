package co.edu.uni.acme.airline.flight.management;

import org.apache.catalina.security.SecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(SecurityConfig.class)
@EntityScan("co.edu.uni.acme.aerolinea.commons")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
