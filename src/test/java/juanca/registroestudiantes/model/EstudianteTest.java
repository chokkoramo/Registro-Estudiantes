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
    @DisplayName("Debe asignar nota valida")
    void testAsignarNotaValida() {
        Estudiante e = new Estudiante();
        e.agregarNota(4.5);
        assertTrue(e.getNotas().contains(4.5));
    }

    @Test
    @DisplayName("Debe lanzar excepcion por nota mayor a 5")
    void testAgregarNotaMayorACinco(){
        Estudiante e = new Estudiante();
        assertThrows(IllegalArgumentException.class, () -> e.agregarNota(6));
    }

    @Test
    @DisplayName("Debe lanzar excepcion por nota menor a 0")
    void testAgregarNotaMenorACero(){
        Estudiante e = new Estudiante();
        assertThrows(IllegalArgumentException.class, () -> e.agregarNota(-2));
    }

    @Test
    void testValoresLimites(){
        Estudiante e = new Estudiante();

        assertDoesNotThrow(() -> e.agregarNota(5.0));
        assertDoesNotThrow(() -> e.agregarNota(0.0));
    }

    @Test
    @DisplayName("Debe ingresar los datos correctamente al constructor")
    void testConstructorConParametros() {
        String nombreEsperado = "Juan";
        String programaEsperado = "Ingeniería";

        Estudiante estudiante = new Estudiante(nombreEsperado, programaEsperado);

        assertEquals(nombreEsperado, estudiante.getNombre());
        assertEquals(programaEsperado, estudiante.getPrograma());
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
    void testNombreNull(){
        assertThrows(IllegalArgumentException.class, () -> new Estudiante(1L, null, "Psicologia"));
    }

    @Test
    void testProgramaNull(){
        assertThrows(IllegalArgumentException.class, () -> new Estudiante(1L, "Juanito", null));
    }

    @Test
    void testConstructorConNotaInicial(){
        Estudiante estudiante = new Estudiante("Sofia", "Medicina", 4.2);

        assertEquals(List.of(4.2), estudiante.getNotas());
    }

    @Test
    void testConstructorConNotaNull(){
        Estudiante estudiante = new Estudiante("Sofia", "Medicina", null);

        assertTrue(estudiante.getNotas().isEmpty());
    }

    @Test
    void testGetNotasNoPermiteModificarLista() {
        Estudiante estudiante = new Estudiante(1L, "Alejandro", "Cine");
        estudiante.agregarNota(4.5);

        List<Double> notas = estudiante.getNotas();

        assertThrows(
                UnsupportedOperationException.class,
                () -> notas.add(3.0)
        );
    }

    @Test
    void testValidarRangoFueraDeLimites(){
        assertAll(
                () -> assertFalse(Estudiante.validarRango(-0.1)),
                () -> assertFalse(Estudiante.validarRango(5.1))
        );
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
