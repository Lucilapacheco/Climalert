package ar.edu.utn.ba.ddsi.climalert_vivo.services.impl;

import ar.edu.utn.ba.ddsi.climalert_vivo.config.AlertProperties;
import ar.edu.utn.ba.ddsi.climalert_vivo.domain.Clima;
import org.springframework.stereotype.Component;

@Component
public class EvaluadorDeAlertas {
  private final AlertProperties properties;

  public EvaluadorDeAlertas(AlertProperties properties) {
    this.properties = properties;
  }

  public boolean esClimaCritico(Clima clima) {
    return clima.temperaturaCelsius() > properties.temperaturaMinima() && clima.humedad() > properties.humedadMinima();
  }

}
