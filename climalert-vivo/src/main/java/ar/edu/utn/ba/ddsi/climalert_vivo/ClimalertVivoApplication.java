package ar.edu.utn.ba.ddsi.climalert_vivo;

import ar.edu.utn.ba.ddsi.climalert_vivo.config.AlertProperties;
import ar.edu.utn.ba.ddsi.climalert_vivo.config.WeatherApiProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties({
	WeatherApiProperties.class, AlertProperties.class})
public class ClimalertVivoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClimalertVivoApplication.class, args);
	}

}
