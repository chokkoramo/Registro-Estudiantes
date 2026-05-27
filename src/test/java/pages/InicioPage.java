package pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class InicioPage {
    private final Page page;

    public InicioPage(Page page) {
        this.page = page;
    }

    public void irAInicio() {
        page.navigate("http://localhost:3001");
    }

    public void irARegistro() {
        page.getByRole(AriaRole.LINK,
                new Page.GetByRoleOptions().setName("Registrar Estudiante")
        ).click();
    }

    public void irAListado() {
        page.getByRole(AriaRole.LINK,
                new Page.GetByRoleOptions().setName("Ver Listado")
        ).click();
    }
}