package juanca.registroestudiantes.dto;

import juanca.registroestudiantes.model.Estudiante;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EstudianteResponseDTO {
    private Long id;
    private String nombre;
    private String programa;
    private double promedio;
    private boolean aprobado;

}
