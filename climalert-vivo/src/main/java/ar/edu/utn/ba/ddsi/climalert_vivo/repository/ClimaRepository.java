package ar.edu.utn.ba.ddsi.climalert_vivo.repository;

import ar.edu.utn.ba.ddsi.climalert_vivo.domain.Clima;

import java.util.List;
import java.util.Optional;

public interface ClimaRepository {
  void guardar(Clima clima);

  Optional<Clima> obtenerUltimo();

  List<Clima> obtenerTodos();
}
