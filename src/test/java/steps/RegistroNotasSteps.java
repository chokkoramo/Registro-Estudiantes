package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import juanca.registroestudiantes.model.Estudiante;

import static org.junit.jupiter.api.Assertions.*;

public class RegistroNotasSteps {
    private double nota;
    private Estudiante estudiante;

    @Given("Crear estudiante con nombre {string} y programa {string} despues se le asigna una nota de {double}")
    public void crearEstudianteConNota(String nombre, String programa, double nota) {
        this.nota = nota;
        this.estudiante = new Estudiante(nombre, programa);
    }

    @When("Se valida dentro del rango")
    public void validarRango(){
        if(estudiante.validarRango(nota)){
            estudiante.agregarNota(nota);
        }
    }

    @Then("El sistema registra la nota")
    public void registrarNota(){
        assertAll(
                ()->assertTrue(estudiante.getNotas().contains(nota),
                        "La nota se tiene que registrar"),
                ()->assertEquals(1,estudiante.getNotas().size())
        );

    }

}
