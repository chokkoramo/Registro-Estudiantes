package juanca.registroestudiantes.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ErrorResponseDTO {
    private String mensaje;
    private List<String> errores;
}
