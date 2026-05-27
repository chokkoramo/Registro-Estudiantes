package pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class RegistroPage {
    private final Page page;

    public RegistroPage(Page page){this.page=page;}

    public void URL(){
        page.navigate("registro-estudiantes.onrender.com/api/estudiantes");
    }

    public void rellenarFormulario(String nombre, String programa, String labelNombre, String labelPrograma){
        page.getByLabel(labelNombre).fill(nombre);
        page.getByLabel(labelPrograma).fill(programa);
    }

    public void hacerClickEnRegistrar(){
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Registrar")).click();
    }
}
