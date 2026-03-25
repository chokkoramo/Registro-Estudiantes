package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import juanca.registroestudiantes.model.Estudiante;

import static org.junit.jupiter.api.Assertions.*;

public class ValidarPromediosSteps {
    private Estudiante estudiante;
    private double promedio;

    @Given("un estudiante con notas {double}, {double} y {double}")
    public void estudianteConNotas(double n1, double n2, double n3) {
        estudiante = new Estudiante("Juan", "Ingenieria");
        estudiante.agregarNota(n1);
        estudiante.agregarNota(n2);
        estudiante.agregarNota(n3);
    }

    @When("se calcula el promedio")
    public void calcularPromedio() {
        promedio = estudiante.calcularPromedio();
    }

    @Then("el promedio debe ser {double}")
    public void validarPromedio(double esperado) {
        assertEquals(esperado, promedio);
    }

    @Then("el estado del estudiante debe ser {string}")
    public void validarEstado(String estadoEsperado) {
        boolean aprobado = estudiante.estaAprobado();

        if (estadoEsperado.equals("APROBADO")) {
            assertTrue(aprobado);
        } else if (estadoEsperado.equals("REPROBADO")) {
            assertFalse(aprobado);
        }
    }

}
