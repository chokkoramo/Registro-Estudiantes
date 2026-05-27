package pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class GestionEstudiantePage {

    private final Page page;

    public GestionEstudiantePage(Page page) {
        this.page = page;
    }

    public void ingresarId(String id) {
        page.getByRole(AriaRole.SPINBUTTON,
                new Page.GetByRoleOptions().setName("ID del Estudiante:")
        ).fill(id);
    }

    public void ingresarNotas(String notas) {
        page.getByRole(AriaRole.TEXTBOX,
                new Page.GetByRoleOptions().setName("Ej: 4.5, 3.2, 5.0")
        ).fill(notas);
    }

    public void clickGuardarNotas() {
        page.getByRole(AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Guardar Notas")
        ).click();
    }

    public String obtenerResultado() {
        return page.locator("text=Éxito").textContent();
    }
}