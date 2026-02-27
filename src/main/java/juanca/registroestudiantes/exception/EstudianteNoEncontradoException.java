package juanca.registroestudiantes.exception;

public class EstudianteNoEncontradoException extends RuntimeException {
    public EstudianteNoEncontradoException(Long  id) {
        super("Estudiante con id "+id+" no existe");
    }
}
