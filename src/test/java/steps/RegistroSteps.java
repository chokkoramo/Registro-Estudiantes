package steps;

import com.microsoft.playwright.Page;
import hooks.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.InicioPage;
import pages.RegistroPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class RegistroSteps {

    private final InicioPage inicioPage;
    private final RegistroPage registroPage;

    public RegistroSteps() {
        Page page = Hooks.page;
        inicioPage = new InicioPage(page);
        registroPage = new RegistroPage(page);
    }

    @Given("que el usuario esta en el inicio")
    public void abrirInicio() {
        inicioPage.irAInicio();
    }

    @When("el usuario hace click en registrar estudiante")
    public void clickRegistrar() {
        inicioPage.irARegistro();
    }

    @When("ingresa el nombre {string} y el programa {string}")
    public void ingresarDatos(String nombre, String programa) {
        registroPage.ingresarNombre(nombre);
        registroPage.ingresarPrograma(programa);
    }

    @When("hace click en registrar")
    public void clickBoton() {
        registroPage.clickRegistrar();
    }

    @Then("el sistema debe mostrar un mensaje de confirmacion {string}")
    public void validarMensaje(String mensajeEsperado) {
        assertThat(
                Hooks.page.getByTestId("mensaje-notificacion")
        ).hasText(mensajeEsperado);
    }
}
