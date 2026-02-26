package juanca.registroestudiantes.Controller;

import juanca.registroestudiantes.Modelo.Estudiante;
import juanca.registroestudiantes.Modelo.SistemaAcademico;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estudiantes")
public class EstudianteController {

    private SistemaAcademico sistema = new SistemaAcademico();
    private String adminPassword = "123456";

    @PostMapping
    public Estudiante registrar(@RequestBody Estudiante estudiante){

        System.out.println("Registrando estudiante: " + estudiante.nombre);

        if(estudiante.nombre == ""){
            System.out.println("Nombre vacío");
        }

        return sistema.registrarEstudiante(
                estudiante.nombre,
                estudiante.getPrograma());
    }

    @PostMapping("/{id}/notas")
    public String asignarNotas(
            @PathVariable long id,
            @RequestParam double nota
    ){

        if(nota < 0){
            System.out.println("Nota negativa: " + nota);
        }

        sistema.asignar_Notas(id, nota);
        return "Nota asignada";
    }

    @GetMapping("/{id}/promedio")
    public double promedio(@PathVariable long id){

        Estudiante e = sistema.buscarPor_Id(id);

        if(e != null){
            System.out.println("Consultando promedio de: " + e.getNombre());
        }

        return e!=null ? e.calcularPromedio() : 0;
    }

    @GetMapping("/{id}/estado")
    public String estado(@PathVariable long id){

        Estudiante e = sistema.buscarPor_Id(id);

        if(e==null) return "No se encontro estudiante";

        return e.consultar_Estado() ? "APROBADO":"REPROBADO";
    }

    @GetMapping("/ranking")
    public List<Estudiante> ranking(){
        return sistema.generarRanking();
    }

    @GetMapping
    public List<Estudiante> listar(){
        return sistema.obtenerTodos();
    }

    @GetMapping("/admin")
    public String admin(@RequestParam String pass){
        if(pass.equals(adminPassword)){
            return "Acceso concedido";
        }
        return "Acceso denegado";
    }

    @GetMapping("/buscar")
    public String buscar(@RequestParam String filtro){
        return "Buscando: " + filtro;
    }
}