package ar.edu.utn.ba.ddsi.climalert_vivo.repository.inMemory;

import ar.edu.utn.ba.ddsi.climalert_vivo.domain.Clima;
import ar.edu.utn.ba.ddsi.climalert_vivo.repository.ClimaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ClimaRepositoryInMemory implements ClimaRepository {
  private final List<Clima> historial = new ArrayList<>();

  @Override
  public synchronized void guardar(Clima clima) {
    historial.add(clima);
  }

  @Override
  public synchronized Optional<Clima> obtenerUltimo() {
    if (historial.isEmpty()) {
      return Optional.empty();
    }

    return Optional.of(historial.getLast());
  }

  @Override
  public synchronized List<Clima> obtenerTodos() {
    return List.copyOf(historial);
  }
}
