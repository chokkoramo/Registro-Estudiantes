package juanca.registroestudiantes.service;

import juanca.registroestudiantes.exception.EstudianteNoEncontradoException;
import juanca.registroestudiantes.model.Estudiante;
import juanca.registroestudiantes.repository.EstudianteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Service
public class SistemaAcademico {

    private final EstudianteRepository estudianteRepository;

    public SistemaAcademico(EstudianteRepository estudianteRepository) {
        this.estudianteRepository = estudianteRepository;
    }

    public Estudiante registrarEstudiante(String nombre, String programa) {
        return estudianteRepository.save(new Estudiante(nombre, programa));
    }

    public Estudiante buscarPorId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id no puede ser null");
        }

        return estudianteRepository.findById(id).orElse(null);
    }

    @Transactional
    public void asignarNota(Long id, double nota) {
        Estudiante estudiante = estudianteRepository.findById(id)
                .orElseThrow(() -> new EstudianteNoEncontradoException(id));

        estudiante.agregarNota(nota);
        estudianteRepository.save(estudiante);
    }

    public List<Estudiante> generarRanking() {
        return estudianteRepository.findAll()
                .stream()
                .sorted(Comparator.comparingDouble(Estudiante::calcularPromedio).reversed())
                .toList();
    }

    public List<Estudiante> obtenerTodos() {
        return estudianteRepository.findAll();
    }

    public void eliminarEstudiante(Long id) {
        Estudiante estudiante = estudianteRepository.findById(id)
                .orElseThrow(() -> new EstudianteNoEncontradoException(id));
        estudianteRepository.delete(estudiante);
    }
}
