package juanca.registroestudiantes.controller;

import juanca.registroestudiantes.model.Estudiante;
import juanca.registroestudiantes.model.SistemaAcademico;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estudiantes")
public class EstudianteController {

    private final SistemaAcademico sistema;

    public EstudianteController(SistemaAcademico sistema) {
        this.sistema = sistema;
    }

    @PostMapping
    public Estudiante registrar(@RequestBody Estudiante estudiante){
        return sistema.registrarEstudiante(
                estudiante.getNombre(),
                estudiante.getPrograma()
        );
    }

    @PostMapping("/{id}/notas")
    public String asignarNota(@PathVariable Long id,
                              @RequestBody double nota){
        sistema.asignarNota(id, nota);
        return "Se asigno" + nota;
    }

    @GetMapping("/{id}/promedio")
    public double promedio(@PathVariable Long id) throws RuntimeException {
        Estudiante e = sistema.buscarPorId(id);

        if(e == null) throw new RuntimeException("Estudiante no encontrado");
        return e.calcularPromedio();
    }

    @GetMapping("/{id}/estado")
    public String estado(@PathVariable Long id){

        Estudiante e = sistema.buscarPorId(id);

        if(e==null){
            throw new RuntimeException("Estudiante no encontrado");
        }

        return e.estaAprobado() ? "APROBADO":"REPROBADO";
    }

    @GetMapping("/ranking")
    public List<Estudiante> ranking(){
        return sistema.generarRanking();
    }

    @GetMapping
    public List<Estudiante> listar(){
        return sistema.obtenerTodos();
    }

}