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
    void testBuscarIdInexistente() {
        SistemaAcademico sa = new SistemaAcademico();
        sa.registrarEstudiante("Juan",  "Software");

        assertNull(sa.buscarPorId(99999L));
    }
}
