package juanca.registroestudiantes.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SistemaAcademicoTest {

    @Test
    @DisplayName("Debe incrementar la id al registrar estudiantes")
    void testRegistrarIncrementoId(){
        SistemaAcademico sa = new SistemaAcademico();
        Estudiante e1 = sa.registrarEstudiante("Juan",  "Software");
        Estudiante e2 = sa.registrarEstudiante("Jose",  "Software");

        assertEquals(1L, e1.getId());
        assertEquals(2L, e2.getId());
    }

    @Test
    void testNombreNull(){
        SistemaAcademico sa = new SistemaAcademico();
        assertThrows(IllegalArgumentException.class, () -> sa.registrarEstudiante(null,"Software"));
    }

    @Test
    @DisplayName("Debe buscar ID existente")
    void testBuscarIdExistente(){
        SistemaAcademico sa = new SistemaAcademico();
        sa.registrarEstudiante("Juan",  "Software");

        assertNotNull(sa.buscarPorId(1L));
    }

    @Test
    void testBuscarIdInexistente() {
        SistemaAcademico sa = new SistemaAcademico();
        sa.registrarEstudiante("Juan",  "Software");

        assertNull(sa.buscarPorId(99999L));
    }

    @Test
    void testGenerarRanking(){
        SistemaAcademico sa = new SistemaAcademico();
        assertTrue(sa.generarRanking().isEmpty());
    }



    @Test
    void testListarEstudiantesVacios(){
        SistemaAcademico sa = new SistemaAcademico();
        assertTrue(sa.obtenerTodos().isEmpty());
    }
}
