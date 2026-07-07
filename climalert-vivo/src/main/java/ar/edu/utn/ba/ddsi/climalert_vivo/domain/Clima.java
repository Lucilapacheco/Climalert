package ar.edu.utn.ba.ddsi.climalert_vivo.domain;

import java.time.LocalDateTime;

public record Clima(
    LocalDateTime fechaConsulta,
    String ubicacion,
    double temperaturaCelsius,
    int humedad,
    String condicion
) {
}
