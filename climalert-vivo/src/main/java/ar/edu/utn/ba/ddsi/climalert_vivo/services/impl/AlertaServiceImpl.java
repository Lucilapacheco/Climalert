package ar.edu.utn.ba.ddsi.climalert_vivo.services.impl;

import org.springframework.stereotype.Service;
import ar.edu.utn.ba.ddsi.climalert_vivo.domain.Clima;
import ar.edu.utn.ba.ddsi.climalert_vivo.services.AlertaService;
import ar.edu.utn.ba.ddsi.climalert_vivo.services.ClimaService;
import ar.edu.utn.ba.ddsi.climalert_vivo.services.NotificacionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@Service
public class AlertaServiceImpl {
  private static final Logger LOGGER = LoggerFactory.getLogger(AlertaServiceImpl.class);

  private final ClimaService climaService;
  private final EvaluadorDeAlertas evaluadorDeAlertas;
  private final NotificacionService notificacionService;

  public AlertaServiceImpl(
      ClimaService climaService,
      EvaluadorDeAlertas evaluadorDeAlertas,
      NotificacionService notificacionService
  ) {
    this.climaService = climaService;
    this.evaluadorDeAlertas = evaluadorDeAlertas;
    this.notificacionService = notificacionService;
  }

  @Override
  public void analizarUltimoClima() {
    climaService.obtenerUltimoClima()
        .ifPresentOrElse(
            this::analizarClima,
            () -> LOGGER.info("Todavía no hay registros de clima para analizar")
        );
  }

  private void analizarClima(Clima clima) {
    if (evaluadorDeAlertas.esClimaCritico(clima)) {
      LOGGER.warn(
          "Clima crítico detectado. Temperatura: {}°C, Humedad: {}%",
          clima.temperaturaCelsius(),
          clima.humedad()
      );

      notificacionService.notificarAlerta(clima);
    } else {
      LOGGER.info(
          "Clima analizado sin alerta. Temperatura: {}°C, Humedad: {}%",
          clima.temperaturaCelsius(),
          clima.humedad()
      );
    }
  }

}
