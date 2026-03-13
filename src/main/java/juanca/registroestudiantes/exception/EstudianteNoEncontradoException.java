package juanca.registroestudiantes.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EstudianteNoEncontradoException extends RuntimeException {
    public EstudianteNoEncontradoException(Long  id) {
        super("Estudiante con id "+id+" no existe");
    }
}
