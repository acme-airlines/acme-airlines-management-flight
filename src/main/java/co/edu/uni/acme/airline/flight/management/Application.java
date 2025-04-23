package co.edu.uni.acme.airline.flight.management;

import co.edu.uni.acme.aerolinea.commons.configuration.ResourceServerConfig;
import org.apache.catalina.security.SecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication(scanBasePackages = {"co.edu.uni.acme.aerolinea", "co.edu.uni.acme.airline"})
@EntityScan("co.edu.uni.acme.aerolinea")
@ComponentScan(basePackages = {"co.edu.uni.acme.aerolinea", "co.edu.uni.acme.airline"})
@Import(ResourceServerConfig.class)
public class Application {

	public static void main(String[] args) {
		new SpringApplicationBuilder(Application.class)
				.properties("spring.config.name=application")
				.properties("spring.config.location=classpath:/application.properties") // <-- Aquí forzas que use solo ese
				.run(args);
	}
}
