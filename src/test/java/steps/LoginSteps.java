package steps;

import com.microsoft.playwright.Page;
import hooks.Hooks;
import io.cucumber.java.en.*;
import pages.LoginPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LoginSteps {

    private final LoginPage loginPage;
    private String usuarioGenerado;

    public LoginSteps() {
        Page page = Hooks.page;
        loginPage = new LoginPage(page);
    }

    @Given("el usuario abre la pagina de login")
    public void abrirLogin() {
        loginPage.irALogin();
    }

    @When("el usuario se registra con usuario {string} y constrasena {string}")
    public void registrarUsuario(String usuario, String contrasena) {

        usuarioGenerado = usuario + System.currentTimeMillis();

        loginPage.clickCrearCuenta();
        loginPage.ingresarUsuario(usuarioGenerado);
        loginPage.ingresarContrasena(contrasena);
        loginPage.clickRegistrarse();
    }

    @When("el usuario ingresa con {string} y {string}")
    public void login(String usuario, String contrasena) {

        loginPage.ingresarUsuario(usuarioGenerado);
        loginPage.ingresarContrasena(contrasena);
        loginPage.clickIngresar();
    }

    @Then("el usuario debe ver el texto {string} en la pantalla inicial")
    public void validarLoginExitoso(String texto) {

        assertThat(
                Hooks.page.getByText(texto)
        ).isVisible();
    }

    @When("el usuario ingresa {string} y {string}")
    public void validarLogin(String usuario, String password) {
        loginPage.ingresarUsuario(usuario);
        loginPage.ingresarContrasena(password);
        loginPage.clickIngresar();
    }

}