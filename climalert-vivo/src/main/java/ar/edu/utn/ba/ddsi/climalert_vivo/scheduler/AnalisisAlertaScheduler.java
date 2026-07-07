package ar.edu.utn.ba.ddsi.climalert_vivo.scheduler;

import ar.edu.utn.ba.ddsi.climalert_vivo.services.AlertaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class AnalisisAlertaScheduler {

  private static final Logger LOGGER = LoggerFactory.getLogger(AnalisisAlertaScheduler.class);

  private final AlertaService alertaService;

  public AnalisisAlertaScheduler(AlertaService alertaService) {
    this.alertaService = alertaService;
  }

  @Scheduled(fixedRate = 60 * 1000)
  public void analizarUltimoClima() {
    try {
      LOGGER.info("Analizando último registro de clima");
      alertaService.analizarUltimoClima();
    } catch (Exception e) {
      LOGGER.error("Error al analizar alertas meteorológicas", e);
    }
  }
}