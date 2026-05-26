package juanca.registroestudiantes.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EstadoResponseDTO {
    private Long id;
    private String estado;
    private boolean aprobado;
}
