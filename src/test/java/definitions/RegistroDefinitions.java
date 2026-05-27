package definitions;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import steps.RegistroSteps;
import steps.InicioSteps;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegistroDefinitions {
    private Playwright playwright;
    private Browser browser;
    private Page page;
    private RegistroSteps registroSteps;
    private InicioSteps inicioSteps;

    @Before
    public void setup(){
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newPage();

        registroSteps = new RegistroSteps(page);
        inicioSteps = new InicioSteps(page);
    }

    @After
    public void cleanup(){
        if(browser != null) browser.close();
        if(playwright != null) playwright.close();
    }
    @Given("que el usuario esta en la pagina de inicio")
    public void elUsuarioEstaEnLaPaginaDeInicio() {
        inicioSteps.abrirPaginaInicio();
    }

    @When("el usuario hace click en la opcion del menu {string}")
    public void elUsuarioHaceClickEnLaOpcionDelMenu(String opcion) {
        inicioSteps.hacerClickEnOpcionMenu(opcion);
    }

    @And("ingresa el nombre {string} y el programa {string}")
    public void ingresaElNombreYElPrograma(String nombre, String programa) {
        registroSteps.rellenarDatos(nombre, programa);
    }

    @And("hace click en el boton {string}")
    public void hacClickEnElBoton(String boton){
        registroSteps.enviarRegistro();
    }

    @Then("el sistema debe mostrar un mensaje de confirmacion {string}")
    public void elSistemaDebeMostrarUnMensajeDeConfirmacion(String mensajeEsperado){
        String mensajeActual = registroSteps.obtenerMensajeEnPantalla();
        assertEquals(mensajeEsperado, mensajeActual, "El mensaje en pantalla no es el esperado");
    }

}