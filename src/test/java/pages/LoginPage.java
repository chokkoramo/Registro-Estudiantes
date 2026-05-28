package pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class LoginPage {

    private static final String FRONTEND_URL = System.getProperty(
            "frontend.url",
            System.getenv().getOrDefault("FRONTEND_URL", "http://localhost:3001")
    );

    private final Page page;

    public LoginPage(Page page) {
        this.page = page;
    }

    public void irALogin() {
        page.navigate(FRONTEND_URL + "/");
    }

    public void clickCrearCuenta() {
        page.getByRole(AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Crear cuenta")
        ).click();
    }

    public void ingresarUsuario(String usuario) {
        page.locator("input[type='text']").fill(usuario);
    }

    public void ingresarContrasena(String password) {
        page.locator("input[type='password']").fill(password);
    }

    public void clickRegistrarse() {
        page.getByRole(AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Registrarse")
        ).click();
    }

    public void clickIngresar() {
        page.getByRole(AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Ingresar")
        ).click();
    }

    public void esperarMensajeRegistro() {
        page.getByText("Usuario registrado").waitFor();
    }

    public boolean estaEnPantallaPrincipal() {
        return page.getByRole(AriaRole.HEADING,
                new Page.GetByRoleOptions().setName("Bienvenido al Sistema de Estudiantes")
        ).isVisible();
    }
}
