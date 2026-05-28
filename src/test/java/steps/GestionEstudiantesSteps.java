package steps;

import com.microsoft.playwright.Page;
import hooks.Hooks;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.GestionEstudiantePage;
import pages.InicioPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class GestionEstudiantesSteps {

    private final InicioPage inicioPage;
    private final GestionEstudiantePage gestionPage;

    public GestionEstudiantesSteps() {
        Page page = Hooks.page;
        inicioPage = new InicioPage(page);
        gestionPage = new GestionEstudiantePage(page);
    }

    @When("el usuario hace click en Gestionar Estudiantes")
    public void irAGestion() {
        inicioPage.irAGestionar();
    }

    @When("ingresa el ID {string} en el campo de busqueda")
    public void ingresarId(String id) {
        gestionPage.ingresarId(id);
    }

    @When("ingresa las notas {string}")
    public void ingresarNotas(String notas) {
        gestionPage.ingresarNotas(notas);
    }

    @When("da click en Guardar Notas")
    public void guardarNotas() {
        gestionPage.clickGuardarNotas();
    }

    @Then("el usuario ve el mensaje de confirmacion {string}")
    public void validarMensaje(String mensajeEsperado) {
        assertThat(
                Hooks.page.getByText(mensajeEsperado)
        ).isVisible();
    }
}