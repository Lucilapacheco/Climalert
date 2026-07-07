package ar.edu.utn.ba.ddsi.climalert_vivo.services.impl;

import org.springframework.stereotype.Service;

import ar.edu.utn.ba.ddsi.climalert_vivo.client.WeatherApiClient;
import ar.edu.utn.ba.ddsi.climalert_vivo.domain.Clima;
import ar.edu.utn.ba.ddsi.climalert_vivo.dtos.WeatherApiResponse;
import ar.edu.utn.ba.ddsi.climalert_vivo.repository.ClimaRepository;
import ar.edu.utn.ba.ddsi.climalert_vivo.services.ClimaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ClimaServiceImpl {
  private static final Logger LOGGER = LoggerFactory.getLogger(ClimaServiceImpl.class);

  private final WeatherApiClient weatherApiClient;
  private final ClimaRepository climaRepository;

  public ClimaServiceImpl(
      WeatherApiClient weatherApiClient,
      ClimaRepository climaRepository
  ) {
    this.weatherApiClient = weatherApiClient;
    this.climaRepository = climaRepository;
  }

  @Override
  public Clima consultarYGuardarClimaActual() {
    WeatherApiResponse response = weatherApiClient.obtenerClimaActual();

    if (response == null || response.location() == null || response.current() == null) {
      throw new IllegalStateException("WeatherAPI no devolvió información climática válida");
    }

    Clima clima = convertirADominio(response);

    climaRepository.guardar(clima);

    LOGGER.info(
        "Clima guardado. Ubicación: {}, Temperatura: {}°C, Humedad: {}%, Condición: {}",
        clima.ubicacion(),
        clima.temperaturaCelsius(),
        clima.humedad(),
        clima.condicion()
    );

    return clima;
  }

  @Override
  public Optional<Clima> obtenerUltimoClima() {
    return climaRepository.obtenerUltimo();
  }

  @Override
  public List<Clima> obtenerHistorial() {
    return climaRepository.obtenerTodos();
  }

  private Clima convertirADominio(WeatherApiResponse response) {
    String ubicacion = response.location().name() + ", " + response.location().country();

    String condicion = response.current().condition() != null
        ? response.current().condition().text()
        : "Sin condición informada";

    return new Clima(
        LocalDateTime.now(),
        ubicacion,
        response.current().tempC(),
        response.current().humidity(),
        condicion
    );
  }
}
