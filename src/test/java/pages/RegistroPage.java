package pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class RegistroPage {
    private final Page page;

    public RegistroPage(Page page) {
        this.page = page;
    }

    public void ingresarNombre(String nombre) {
        page.getByRole(AriaRole.TEXTBOX,
                new Page.GetByRoleOptions().setName("Nombre:")
        ).fill(nombre);
    }

    public void ingresarPrograma(String programa) {
        page.getByRole(AriaRole.TEXTBOX,
                new Page.GetByRoleOptions().setName("Programa:")
        ).fill(programa);
    }

    public void clickRegistrar() {
        page.getByRole(AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Registrar")
        ).click();
    }
}