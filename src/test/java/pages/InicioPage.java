package pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class InicioPage {
    private final Page page;

    public InicioPage(Page page) {
        this.page=page;
    }

    public void navegarAInicio() {
        page.navigate("http://localhost:3001");
    }

    public void clickEnlaceInicio(){
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Inicio")).click();
    }

    public void clickEnlaceRegistrar(){
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Registrar Estudiante")).click();
    }

    public void clickEnlaceListado(){
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Ver Listado")).click();
    }

    public void clickEnlaceGestionar(){
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Gestionar Estudiantes")).click();
    }

}
