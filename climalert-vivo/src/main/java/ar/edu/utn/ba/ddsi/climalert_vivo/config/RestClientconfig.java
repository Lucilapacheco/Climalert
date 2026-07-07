package ar.edu.utn.ba.ddsi.climalert_vivo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientconfig {

  @Bean
  public RestClient weatherRestClient(WeatherApiProperties properties){
    return RestClient.builder().baseUrl(properties.baseurl()).build();
  }
}
