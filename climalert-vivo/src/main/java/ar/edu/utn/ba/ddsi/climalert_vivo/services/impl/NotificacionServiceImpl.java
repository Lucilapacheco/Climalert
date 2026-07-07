package ar.edu.utn.ba.ddsi.climalert_vivo.services.impl;

import ar.edu.utn.ba.ddsi.climalert_vivo.config.AlertProperties;
import ar.edu.utn.ba.ddsi.climalert_vivo.domain.Clima;
import ar.edu.utn.ba.ddsi.climalert_vivo.services.NotificacionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificacionServiceImpl implements NotificacionService {

  private static final Logger LOGGER = LoggerFactory.getLogger(NotificacionServiceImpl.class);

  private final JavaMailSender javaMailSender;
  private final AlertProperties properties;

  public NotificacionServiceImpl(
      JavaMailSender javaMailSender,
      AlertProperties properties
  ) {
    this.javaMailSender = javaMailSender;
    this.properties = properties;
  }

  @Override
  public void notificarAlerta(Clima clima) {
    SimpleMailMessage mensaje = new SimpleMailMessage();

    mensaje.setTo(properties.destinatarios().toArray(String[]::new));
    mensaje.setSubject("Alerta meteorológica - Climalert");
    mensaje.setText(generarCuerpo(clima));

    try {
      javaMailSender.send(mensaje);

      LOGGER.info(
          "Alerta enviada por correo a {} destinatarios",
          properties.destinatarios().size()
      );
    } catch (Exception e) {
      LOGGER.error("No se pudo enviar la alerta por correo", e);
    }
  }

  private String generarCuerpo(Clima clima) {
    return """
                Se detectaron condiciones climáticas críticas.

                Detalle del clima:

                Fecha de consulta: %s
                Ubicación: %s
                Temperatura: %.2f °C
                Humedad: %d %%
                Condición: %s

                Criterio de alerta:
                Temperatura mayor a %.2f °C y humedad mayor a %d %%.
                """.formatted(
        clima.fechaConsulta(),
        clima.ubicacion(),
        clima.temperaturaCelsius(),
        clima.humedad(),
        clima.condicion(),
        properties.temperaturaMinima(),
        properties.humedadMinima()
    );
  }
}