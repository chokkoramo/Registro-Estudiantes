package juanca.registroestudiantes.controller;

import juanca.registroestudiantes.dto.*;
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
}