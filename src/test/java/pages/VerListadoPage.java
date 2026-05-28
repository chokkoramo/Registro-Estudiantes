package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class VerListadoPage {
    private final Page page;

    public VerListadoPage(Page page) {
        this.page = page;
    }

    public void esperarTabla() {
        page.getByTestId("fila-estudiante").first().waitFor();
    }

    public Locator obtenerEstudiantePorNombre(String nombre) {
        return page.getByTestId("nombre-estudiante")
                .filter(new Locator.FilterOptions().setHasText(nombre));
    }

    public void verTodosEstudiantes(){
        page.getByRole(com.microsoft.playwright.options.AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Ver Todos")
        ).click();
    }
}