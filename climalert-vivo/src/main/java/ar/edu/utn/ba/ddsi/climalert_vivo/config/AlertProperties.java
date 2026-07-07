package ar.edu.utn.ba.ddsi.climalert_vivo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "alertas")
public record AlertProperties(
    double temperaturaMinima,
    int humedadMinima,
    List<String> destinatarios
) {
}
