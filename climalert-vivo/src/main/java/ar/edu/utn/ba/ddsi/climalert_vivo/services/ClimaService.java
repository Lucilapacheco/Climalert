package ar.edu.utn.ba.ddsi.climalert_vivo.services;

import ar.edu.utn.ba.ddsi.climalert_vivo.domain.Clima;

import java.util.List;
import java.util.Optional;

public interface ClimaService {
  Clima consultarYGuardarClimaActual();

  Optional<Clima> obtenerUltimoClima();

  List<Clima> obtenerHistorial();
}
