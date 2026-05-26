package juanca.registroestudiantes.service;

import juanca.registroestudiantes.exception.EstudianteNoEncontradoException;
import juanca.registroestudiantes.model.Estudiante;
import juanca.registroestudiantes.repository.EstudianteRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class SistemaAcademicoTest {

    @Test
    @DisplayName("Debe registrar estudiantes usando el repositorio")
    void testRegistrarEstudiante(){
        EstudianteRepository repository = mock(EstudianteRepository.class);
        SistemaAcademico sistemaAcademico = new SistemaAcademico(repository);
        when(repository.save(any(Estudiante.class)))
                .thenReturn(new Estudiante(1L, "Juan", "Software"));

        Estudiante estudiante = sistemaAcademico.registrarEstudiante("Juan",  "Software");

        assertEquals(1L, estudiante.getId());
        verify(repository).save(any(Estudiante.class));
    }

    @Test
    void testNombreNull(){
        SistemaAcademico sistemaAcademico = new SistemaAcademico(mock(EstudianteRepository.class));
        assertThrows(IllegalArgumentException.class, () -> sistemaAcademico.registrarEstudiante(null,"Software"));
    }

    @Test
    @DisplayName("Debe buscar ID existente")
    void testBuscarIdExistente(){
        EstudianteRepository repository = mock(EstudianteRepository.class);
        SistemaAcademico sistemaAcademico = new SistemaAcademico(repository);
        when(repository.findById(1L)).thenReturn(Optional.of(new Estudiante(1L, "Juan", "Software")));

        assertNotNull(sistemaAcademico.buscarPorId(1L));
    }

    @Test
    void testBuscarIdInexistente() {
        EstudianteRepository repository = mock(EstudianteRepository.class);
        SistemaAcademico sistemaAcademico = new SistemaAcademico(repository);
        when(repository.findById(99999L)).thenReturn(Optional.empty());

        assertNull(sistemaAcademico.buscarPorId(99999L));
    }

    @Test
    void testGenerarRanking(){
        EstudianteRepository repository = mock(EstudianteRepository.class);
        SistemaAcademico sistemaAcademico = new SistemaAcademico(repository);
        when(repository.findAll()).thenReturn(List.of());

        assertTrue(sistemaAcademico.generarRanking().isEmpty());
    }

    @Test
    void testListarEstudiantesVacios(){
        EstudianteRepository repository = mock(EstudianteRepository.class);
        SistemaAcademico sistemaAcademico = new SistemaAcademico(repository);
        when(repository.findAll()).thenReturn(List.of());

        assertTrue(sistemaAcademico.obtenerTodos().isEmpty());
    }

    @Test
    void testObtenerTodosNull(){
        EstudianteRepository repository = mock(EstudianteRepository.class);
        SistemaAcademico sistemaAcademico = new SistemaAcademico(repository);
        when(repository.findAll()).thenReturn(List.of(
                new Estudiante(1L, "Juan", "Software"),
                new Estudiante(2L, "Jose", "Software")
        ));

        assertFalse(sistemaAcademico.obtenerTodos().isEmpty());
    }

    @Test
    void testAsignarNotaEstudianteInexistente() {
        EstudianteRepository repository = mock(EstudianteRepository.class);
        SistemaAcademico sistemaAcademico = new SistemaAcademico(repository);
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(EstudianteNoEncontradoException.class, () -> sistemaAcademico.asignarNota(99L, 4.0));
    }
}
