package juanca.registroestudiantes.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PromedioResponseDTO {
    private Long id;
    private double promedio;
}
