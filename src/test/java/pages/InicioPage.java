package pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class InicioPage {
    private static final String FRONTEND_URL = System.getProperty(
            "frontend.url",
            System.getenv().getOrDefault("FRONTEND_URL", "http://localhost:3001")
    );

    private final Page page;

    public InicioPage(Page page) {
        this.page = page;
    }

    public void irAInicio() {
        page.navigate(FRONTEND_URL);
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

    public void irAGestionar() {
        page.getByRole(AriaRole.LINK,
                new Page.GetByRoleOptions().setName("Gestionar Estudiantes")
        ).click();
    }
}
