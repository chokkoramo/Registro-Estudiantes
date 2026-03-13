package juanca.registroestudiantes.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

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
        assertThrows(IllegalArgumentException.class, () -> estudiante.agregarNota(-1));
    }

    @Test
    @DisplayName("Debe matar mutacion getNotas (devuelve los datos)")
    void testConsultarNotasContenido(){
        Estudiante estudiante = new Estudiante(1L, "Alejandro", "Cine");
        estudiante.agregarNota(4.5);

        List<Double> notas = estudiante.getNotas();

        assertAll(
                ()->assertFalse(notas.isEmpty()),
                ()->assertEquals(1,notas.size()),
                ()->assertEquals(4.5,notas.getFirst())
        );
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

    @Test
    @DisplayName("Debe matar mutacion estaAprobado")
    void testLogicaAprobacion(){
        Estudiante estudiante = new Estudiante(1L, "Carlos", "Mecanica");

        estudiante.agregarNota(2.0);
        assertFalse(estudiante.estaAprobado(), "Con 2.0 no deberia estar aprobado");

        estudiante.agregarNota(4.0);
        assertTrue(estudiante.estaAprobado(), "Con 4.0 deberia estar aprobado");
    }


}
