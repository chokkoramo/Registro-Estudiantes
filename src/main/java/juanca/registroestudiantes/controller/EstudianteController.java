package juanca.registroestudiantes.controller;

import jakarta.validation.Valid;
import juanca.registroestudiantes.dto.*;
import juanca.registroestudiantes.exception.EstudianteNoEncontradoException;
import juanca.registroestudiantes.model.Estudiante;
import juanca.registroestudiantes.service.SistemaAcademico;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estudiantes")
public class EstudianteController {

    private final SistemaAcademico sistema;

    public EstudianteController(SistemaAcademico sistema) {
        this.sistema = sistema;
    }

    @PostMapping
    public ResponseEntity<EstudianteResponseDTO> registrar(@Valid @RequestBody EstudianteRequestDTO dto){
        Estudiante estudiante = sistema.registrarEstudiante(
                dto.getNombre(),
                dto.getPrograma()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(convertir(estudiante));
    }

    @PostMapping("/{id}/notas")
    public MensajeResponseDTO asignarNotas(@PathVariable Long id,
                                           @Valid @RequestBody List<@Valid NotaDTO> notas){

        notas.forEach(n -> sistema.asignarNota(id, n.getNota()));

        return MensajeResponseDTO.builder()
                .mensaje("Se asignaron " + notas.size() + " notas")
                .totalNotas(notas.size())
                .build();
    }

    @GetMapping("/{id}/promedio")
    public PromedioResponseDTO promedio(@PathVariable Long id) {
        Estudiante e = sistema.buscarPorId(id);

        if(e == null) throw new EstudianteNoEncontradoException(id);
        return PromedioResponseDTO.builder()
                .id(e.getId())
                .promedio(e.calcularPromedio())
                .build();
    }

    @GetMapping("/{id}/estado")
    public EstadoResponseDTO estado(@PathVariable Long id){

        Estudiante e = sistema.buscarPorId(id);

        if(e==null){
            throw new EstudianteNoEncontradoException(id);
        }

        boolean aprobado = e.estaAprobado();

        return EstadoResponseDTO.builder()
                .id(e.getId())
                .estado(aprobado ? "APROBADO":"REPROBADO")
                .aprobado(aprobado)
                .build();
    }

    @GetMapping("/ranking")
    public List<EstudianteResponseDTO> ranking(){
        return sistema.generarRanking()
                .stream()
                .map(this::convertir)
                .toList();
    }

    @GetMapping
    public List<EstudianteResponseDTO> listar(){
        return sistema.obtenerTodos()
                .stream()
                .map(this::convertir)
                .toList();
    }

    private EstudianteResponseDTO convertir(Estudiante estudiante) {
        return EstudianteResponseDTO.builder()
                .id(estudiante.getId())
                .nombre(estudiante.getNombre())
                .programa(estudiante.getPrograma())
                .promedio(estudiante.calcularPromedio())
                .aprobado(estudiante.estaAprobado())
                .build();
    }
}
