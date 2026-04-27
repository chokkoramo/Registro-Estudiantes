package juanca.registroestudiantes.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SistemaAcademicoTest {

    @Test
    @DisplayName("Debe incrementar la id al registrar estudiantes")
    void testRegistrarIncrementoId(){
        SistemaAcademico sistemaAcademico = new SistemaAcademico();
        Estudiante eestudiante1 = sistemaAcademico.registrarEstudiante("Juan",  "Software");
        Estudiante estudiante2 = sistemaAcademico.registrarEstudiante("Jose",  "Software");

        assertEquals(1L, eestudiante1.getId());
        assertEquals(2L, estudiante2.getId());
    }

    @Test
    void testNombreNull(){
        SistemaAcademico sistemaAcademico = new SistemaAcademico();
        assertThrows(IllegalArgumentException.class, () -> sistemaAcademico.registrarEstudiante(null,"Software"));
    }

    @Test
    @DisplayName("Debe buscar ID existente")
    void testBuscarIdExistente(){
        SistemaAcademico sistemaAcademico = new SistemaAcademico();
        sistemaAcademico.registrarEstudiante("Juan",  "Software");

        assertNotNull(sistemaAcademico.buscarPorId(1L));
    }

    @Test
    void testBuscarIdInexistente() {
        SistemaAcademico sistemaAcademico = new SistemaAcademico();
        sistemaAcademico.registrarEstudiante("Juan",  "Software");

        assertNull(sistemaAcademico.buscarPorId(99999L));
    }

    @Test
    void testGenerarRanking(){
        SistemaAcademico sistemaAcademico = new SistemaAcademico();
        assertTrue(sistemaAcademico.generarRanking().isEmpty());
    }

    @Test
    void testListarEstudiantesVacios(){
        SistemaAcademico sistemaAcademico = new SistemaAcademico();
        assertTrue(sistemaAcademico.obtenerTodos().isEmpty());
    }

    @Test
    void testObtenerTodosNull(){
        SistemaAcademico sistemaAcademico = new SistemaAcademico();
        sistemaAcademico.registrarEstudiante("Juan",  "Software");
        sistemaAcademico.registrarEstudiante("Jose",  "Software");

        assertFalse(sistemaAcademico.obtenerTodos().isEmpty());
    }
}
