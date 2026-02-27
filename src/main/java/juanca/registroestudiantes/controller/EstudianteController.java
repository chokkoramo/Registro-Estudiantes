package juanca.registroestudiantes.controller;

import juanca.registroestudiantes.dto.EstudianteRequestDTO;
import juanca.registroestudiantes.dto.EstudianteResponseDTO;
import juanca.registroestudiantes.dto.NotaDTO;
import juanca.registroestudiantes.exception.EstudianteNoEncontradoException;
import juanca.registroestudiantes.model.Estudiante;
import juanca.registroestudiantes.model.SistemaAcademico;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/estudiantes")
public class EstudianteController {

    private final SistemaAcademico sistema;

    public EstudianteController(SistemaAcademico sistema) {
        this.sistema = sistema;
    }

    @PostMapping
    public EstudianteResponseDTO registrar(@RequestBody EstudianteRequestDTO dto){
        Estudiante estudiante = sistema.registrarEstudiante(
                dto.getNombre(),
                dto.getPrograma()
        );

        return EstudianteResponseDTO.builder()
                .id(estudiante.getId())
                .nombre(estudiante.getNombre())
                .programa(estudiante.getPrograma())
                .promedio(estudiante.calcularPromedio())
                .aprobado(estudiante.estaAprobado())
                .build();
    }

    @PostMapping("/{id}/notas")
    public String asignarNota(@PathVariable Long id,
                              @RequestBody NotaDTO dto){
        sistema.asignarNota(id, dto.getNota());
        return "Se asigno " + dto.getNota();
    }

    @GetMapping("/{id}/promedio")
    public double promedio(@PathVariable Long id) throws RuntimeException {
        Estudiante e = sistema.buscarPorId(id);

        if(e == null) throw new EstudianteNoEncontradoException(id);
        return e.calcularPromedio();
    }

    @GetMapping("/{id}/estado")
    public String estado(@PathVariable Long id){

        Estudiante e = sistema.buscarPorId(id);

        if(e==null){
            throw new EstudianteNoEncontradoException(id);
        }

        return e.estaAprobado() ? "APROBADO":"REPROBADO";
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