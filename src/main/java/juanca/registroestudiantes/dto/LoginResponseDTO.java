package juanca.registroestudiantes.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginResponseDTO {
    private Long id;
    private String username;
    private String mensaje;
}
