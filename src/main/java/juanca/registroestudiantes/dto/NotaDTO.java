package juanca.registroestudiantes.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotaDTO {
    @DecimalMin(value = "0.0", message = "La nota minima es 0.0")
    @DecimalMax(value = "5.0", message = "La nota maxima es 5.0")
    private double nota;
}
