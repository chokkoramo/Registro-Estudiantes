package hooks;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import pages.LoginPage;

public class Hooks {
    public static Playwright playwright;
    public static Browser browser;
    public static Page page;

    private static final String LOGIN_USER = "user_admin";
    private static final String LOGIN_PASSWORD = "admin_contra";
    private static boolean usuarioCreado = false;

    @Before(order = 0)
    public void setUp() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
        page = browser.newPage();
    }

    @Before(value = "@2E2 and not @Login", order = 1)
    public void loginAntesDePrueba() {

        LoginPage loginPage = new LoginPage(page);
        loginPage.irALogin();

        if (!usuarioCreado) {
            loginPage.clickCrearCuenta();
            loginPage.ingresarUsuario(LOGIN_USER);
            loginPage.ingresarContrasena(LOGIN_PASSWORD);
            loginPage.clickRegistrarse();

            usuarioCreado = true;
        }

        loginPage.ingresarUsuario(LOGIN_USER);
        loginPage.ingresarContrasena(LOGIN_PASSWORD);
        loginPage.clickIngresar();
        loginPage.estaEnPantallaPrincipal();
    }

    @After
    public void tearDown() {
        browser.close();
        playwright.close();
    }
}
