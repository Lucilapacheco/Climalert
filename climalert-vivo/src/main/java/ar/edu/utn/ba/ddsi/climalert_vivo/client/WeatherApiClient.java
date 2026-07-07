package ar.edu.utn.ba.ddsi.climalert_vivo.client;

import ar.edu.utn.ba.ddsi.climalert_vivo.dtos.WeatherApiResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class WeatherApiClient {

  private final RestClient weatherRestClient;
  private final WeatherApiClient properties;

  public WeatherApiClient(RestClient weatherRestClient, WeatherApiClient properties) {
    this.weatherRestClient = weatherRestClient;
    this.properties = properties;
  }

  public WeatherApiResponse obtenerClimaActual() {
    return weatherRestClient.get()
        .uri(uriBuilder -> uriBuilder
            .path("/current.json")
            .queryParam("key", properties.apiKey())
            .queryParam("q", properties.location())
            .build())
        .retrieve()
        .body(WeatherApiResponse.class);
  }
}
