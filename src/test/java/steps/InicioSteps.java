package steps;

import com.microsoft.playwright.Page;
import pages.InicioPage;

public class InicioSteps {
    private final InicioPage inicioPage;

    public InicioSteps(Page page) {
        this.inicioPage = new InicioPage(page);
    }

    public void abrirPaginaInicio() {
        inicioPage.navegarAInicio();
    }

    public void hacerClickEnOpcionMenu(String opcion) {
        switch (opcion.toLowerCase()) {
            case "inicio":
                inicioPage.clickEnlaceInicio();
                break;
            case "registrar estudiante":
                inicioPage.clickEnlaceRegistrar();
                break;
            case "ver listado":
                inicioPage.clickEnlaceListado();
                break;
            case "gestionar estudiantes":
                inicioPage.clickEnlaceGestionar();
                break;
            default:
                throw new IllegalArgumentException("La opción de menú '" + opcion + "' no existe.");
        }
    }
}
