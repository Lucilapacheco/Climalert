package ar.edu.utn.ba.ddsi.climalert_vivo.services;

import ar.edu.utn.ba.ddsi.climalert_vivo.domain.Clima;

public interface NotificacionService {

  void notificarAlerta(Clima clima);
}
