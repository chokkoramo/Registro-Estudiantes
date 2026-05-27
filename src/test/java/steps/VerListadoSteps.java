package steps;

import com.microsoft.playwright.Page;
import hooks.Hooks;
import io.cucumber.java.en.*;
import pages.InicioPage;
import pages.RegistroPage;
import pages.VerListadoPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class VerListadoSteps {

    private final InicioPage inicioPage;
    private final RegistroPage registroPage;
    private final VerListadoPage listadoPage;

    public VerListadoSteps() {
        Page page = Hooks.page;
        inicioPage = new InicioPage(page);
        registroPage = new RegistroPage(page);
        listadoPage = new VerListadoPage(page);
    }

    @Given("que el usuario esta en la pagina de inicio")
    public void abrirInicio() {
        inicioPage.irAInicio();
    }

    @When("el usuario da click en la opcion del menu Registrar Estudiante")
    public void navegarMenu() {
            inicioPage.irARegistro();
    }

    @When("ingresa su nombre {string} y el programa {string}")
    public void ingresarDatos(String nombre, String programa) {
        registroPage.ingresarNombre(nombre);
        registroPage.ingresarPrograma(programa);
    }

    @When("hace click al boton registrar")
    public void clickBoton() {
        registroPage.clickRegistrar();
    }

    @When("el usuario hace click en la opcion del menu ver listado")
    public void clickListado() {
        inicioPage.irAListado();
        listadoPage.verTodosEstudiantes();
    }

    @Then("el sistema debe mostrar a {string} en la lista de estudiantes")
    public void validarListado(String nombre) {
        listadoPage.esperarTabla();

        assertThat(
                listadoPage.obtenerEstudiantePorNombre(nombre).first()
        ).isVisible();
    }
}