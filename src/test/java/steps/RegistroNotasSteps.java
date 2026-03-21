package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import juanca.registroestudiantes.model.Estudiante;

import static org.junit.jupiter.api.Assertions.*;

public class RegistroNotasSteps {
    private double nota;
    private boolean validacion;
    private Estudiante estudiante;

    @Given("Se ingresa una nota de {double}")
    public void ingresarNota(double nota){
        this.nota = nota;
        this.estudiante = new Estudiante("Ingenieria", "Juan");
    }

    @When("Se valida dentro del rango")
    public void validarRango(){
        this.validacion = Estudiante.validarRango(nota);
        if(validacion){
            estudiante.agregarNota(nota);
        }
    }

    @Then("El sistema registra la nota")
    public void registrarNota(){
        assertTrue(estudiante.getNotas().contains(nota),
                "La nota se tiene que registrar");
    }
}
