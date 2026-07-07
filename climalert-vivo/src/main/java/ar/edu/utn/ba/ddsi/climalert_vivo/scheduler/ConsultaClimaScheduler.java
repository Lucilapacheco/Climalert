package ar.edu.utn.ba.ddsi.climalert_vivo.scheduler;

import ar.edu.utn.ba.ddsi.climalert_vivo.services.ClimaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

import org.springframework.stereotype.Component;

@Component
public class ConsultaClimaScheduler {

  private static final Logger LOGGER = LoggerFactory.getLogger(ConsultaClimaScheduler.class);

  private final ClimaService climaService;

  public ConsultaClimaScheduler(ClimaService climaService) {
    this.climaService = climaService;
  }

  @Scheduled(fixedRate = 5 * 60 * 1000)
  public void consultarClimaActual() {
    try {
      LOGGER.info("Consultando clima actual en WeatherAPI");
      climaService.consultarYGuardarClimaActual();
    } catch (Exception e) {
      LOGGER.error("Error al consultar el clima actual", e);
    }
  }
}
