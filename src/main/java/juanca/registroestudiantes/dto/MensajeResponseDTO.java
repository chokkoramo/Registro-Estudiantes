package juanca.registroestudiantes.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MensajeResponseDTO {
    private String mensaje;
    private int totalNotas;
}
