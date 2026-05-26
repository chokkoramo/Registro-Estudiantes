package juanca.registroestudiantes.exception;

import juanca.registroestudiantes.dto.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.util.List;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(EstudianteNoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponseDTO manejarEstudianteNoEncontrado(EstudianteNoEncontradoException exception) {
        return ErrorResponseDTO.builder()
                .mensaje(exception.getMessage())
                .errores(List.of())
                .build();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDTO manejarArgumentoInvalido(IllegalArgumentException exception) {
        return ErrorResponseDTO.builder()
                .mensaje(exception.getMessage())
                .errores(List.of())
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDTO manejarValidaciones(MethodArgumentNotValidException exception) {
        List<String> errores = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .toList();

        return ErrorResponseDTO.builder()
                .mensaje("La solicitud tiene datos invalidos")
                .errores(errores)
                .build();
    }

    @ExceptionHandler(HandlerMethodValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDTO manejarValidacionesDeMetodo(HandlerMethodValidationException exception) {
        List<String> errores = exception.getParameterValidationResults()
                .stream()
                .flatMap(result -> result.getResolvableErrors().stream())
                .map(MessageSourceResolvable::getDefaultMessage)
                .toList();

        return ErrorResponseDTO.builder()
                .mensaje("La solicitud tiene datos invalidos")
                .errores(errores)
                .build();
    }
}
