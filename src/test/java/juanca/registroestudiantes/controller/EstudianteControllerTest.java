package juanca.registroestudiantes.controller;

import juanca.registroestudiantes.dto.*;
import juanca.registroestudiantes.exception.EstudianteNoEncontradoException;
import juanca.registroestudiantes.model.Estudiante;
import juanca.registroestudiantes.model.SistemaAcademico;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EstudianteControllerTest {
    private EstudianteController controller;
    private SistemaAcademico sistemaMock;

    @BeforeEach
    void setUp() {
        sistemaMock = mock(SistemaAcademico.class);
        controller = new EstudianteController(sistemaMock);
    }

    @Test
    @DisplayName("Debe registrar estudiante y retornar DTO")
    void testRegistrarEstudiante(){
        EstudianteRequestDTO request = new EstudianteRequestDTO("Juan", "IngSoftware");
        Estudiante estudiante1 = new Estudiante(1L, "Juan", "IngSoftware");
        when(sistemaMock.registrarEstudiante("Juan", "IngSoftware")).thenReturn(estudiante1);

        EstudianteResponseDTO response = controller.registrar(request);

        assertAll("Respuesta del DTO",
                ()->assertEquals(1L, response.getId()),
                ()->assertEquals("Juan", response.getNombre()),
                ()->assertEquals("IngSoftware", response.getPrograma()),
                ()->assertFalse(response.isAprobado()),
                ()->assertEquals(0.0, response.getPromedio())

        );
    }

    @Test
    @DisplayName("Debe asignar una nota a un estudiante")
    void testAsignarNota(){
        NotaDTO nota = new NotaDTO(4.5);
        String response = controller.asignarNota(1L, nota);

        verify(sistemaMock).asignarNota(1L, 4.5);
        assertEquals("Se asigno 4.5", response);
    }

    @Test
    @DisplayName("Debe retornar el promedio cuando el estudiante existe")
    void testPromedioExito(){
        Estudiante e = new Estudiante(1L, "Juan", "Ing");
        e.agregarNota(5);
        when(sistemaMock.buscarPorId(1L)).thenReturn(e);

        double promedio = controller.promedio(1L);

        assertEquals(5,promedio);
    }

    @Test
    @DisplayName("Debe tirar ecxepcion si el estudiante no exisste")
    void testEstudianteNoEncontrado(){
        when(sistemaMock.buscarPorId(99L)).thenReturn(null);

        assertAll(
                ()->assertThrows(EstudianteNoEncontradoException.class, ()-> controller.promedio(99L)),
                ()->assertThrows(EstudianteNoEncontradoException.class, () -> controller.estado(99L))
        );
    }

    @Test
    void testEstadoAprobado(){
        Estudiante e = mock(Estudiante.class);
        when(e.estaAprobado()).thenReturn(true);
        when(sistemaMock.buscarPorId(1L)).thenReturn(e);

        assertEquals("APROBADO", controller.estado(1L));
    }

    @Test
    @DisplayName("Debe lanzar excepcion si el estudiante no existe")
    void testEstadoError(){
        when(sistemaMock.buscarPorId(1L)).thenReturn(null);
        assertThrows(EstudianteNoEncontradoException.class, ()->controller.estado(1L));
    }

}