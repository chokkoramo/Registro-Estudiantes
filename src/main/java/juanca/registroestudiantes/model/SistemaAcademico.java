package juanca.registroestudiantes.model;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class SistemaAcademico {

    private final List<Estudiante> estudiantes = new ArrayList<>();
    private Long contadorId = 1L;

    public Estudiante registrarEstudiante(String nombre, String programa){
        Estudiante e = new Estudiante(contadorId++, nombre, programa);
        estudiantes.add(e);
        return e;
    }

    public Estudiante buscarPorId(Long id){
        if(id == null){
            throw new IllegalArgumentException("Id no puede ser null");
        }

        return estudiantes.stream()
                .filter(e->e.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void asignarNota(Long id, double nota){
        Estudiante e = buscarPorId(id);
        if(e == null){
            throw new IllegalArgumentException("Estudiante no encontrado");
        }
        e.agregarNota(nota);
    }

    public List<Estudiante> generarRanking(){
        List<Estudiante> ranking = new ArrayList<>(estudiantes);

        ranking.sort(Comparator.comparingDouble(Estudiante::calcularPromedio)
                .reversed());

        return ranking;
    }

    public List<Estudiante> obtenerTodos(){
        return Collections.unmodifiableList(estudiantes);
    }
}
