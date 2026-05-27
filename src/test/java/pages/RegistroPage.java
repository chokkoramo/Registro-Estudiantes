package pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class RegistroPage {
    private final Page page;

    public RegistroPage(Page page) {
        this.page = page;
    }

    public void URL() {
        page.navigate("http://localhost:3001/registro/");
    }

    public void rellenarFormulario(String nombre, String programa) {
        page.getByLabel("Nombre:").fill(nombre);
        page.getByLabel("Programa:").fill(programa);
    }

    public void hacerClickEnRegistrar() {
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Registrar")).click();
    }

    public String obtenerTextoNotificacion() {
        return page.getByTestId("mensaje-notificacion").textContent();
    }
}