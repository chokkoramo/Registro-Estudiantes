package juanca.registroestudiantes.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EstudianteTest {
    @Test
    void testRegistrarEstudiante() {
        Estudiante estudiante = new Estudiante(1L, "Alejandro", "Cine");

        assertNotNull(estudiante);
        assertEquals("Alejandro", estudiante.getNombre());
        assertEquals("Cine", estudiante.getPrograma());
    }

    @Test
    void testAgregarNotasInvalida(){
        Estudiante estudiante = new Estudiante(1L, "Walter", "Administracion de empresas");

        assertThrows(IllegalArgumentException.class, () -> estudiante.agregarNota(6));
    }

    @Test
    void testNombreVacio(){
        assertThrows(IllegalArgumentException.class, () -> new Estudiante(1L, "", "Psicologia"));
    }

    @Test
    void testProgramaVacio(){
        assertThrows(IllegalArgumentException.class, () -> new Estudiante(1L, "Juanito", ""));
    }

    @Test
    void testPromedioValido(){
        Estudiante estudiante = new Estudiante(1L, "Carlos", "Mecanica");

        estudiante.agregarNota(4);
        estudiante.agregarNota(3.2);
        estudiante.agregarNota(5);
        estudiante.agregarNota(1);

        assertAll(
                () -> assertEquals(3.3, estudiante.calcularPromedio()),
                () -> assertTrue(estudiante.estaAprobado()),
                () -> assertNotNull(estudiante.getNotas())
        );
    }


}
