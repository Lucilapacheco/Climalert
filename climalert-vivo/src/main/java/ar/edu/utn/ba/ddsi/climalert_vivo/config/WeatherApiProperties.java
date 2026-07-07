package ar.edu.utn.ba.ddsi.climalert_vivo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "weather-api")
public record WeatherApiProperties(
    String baseUrl,
    String apiKey,
    String location
) {
}
