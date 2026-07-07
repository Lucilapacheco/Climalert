package ar.edu.utn.ba.ddsi.climalert_vivo.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public record WeatherApiResponse(
    Location location,
    Current current
) {
  public record Location(
      String name,
      String region,
      String country,
      @JsonProperty("localtime")
      String localTime
  ) {
  }

  public record Current(
      @JsonProperty("temp_c")
      double tempC,

      int humidity,

      Condition condition
  ) {
  }

  public record Condition(
      String text
  ) {
  }
}
