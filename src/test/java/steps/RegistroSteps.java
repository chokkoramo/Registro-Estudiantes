package steps;

import com.microsoft.playwright.Page;
import pages.RegistroPage;

public class RegistroSteps {

    private final RegistroPage registroPage;

    public RegistroSteps(Page page) {
        this.registroPage = new RegistroPage(page);
    }

    public void abrirFormularioDeRegistro() {
        registroPage.URL();
    }

    public void rellenarDatos(String nombre, String programa) {
        registroPage.rellenarFormulario(nombre, programa);
    }

    public void enviarRegistro() {
        registroPage.hacerClickEnRegistrar();
    }

    public String obtenerMensajeEnPantalla() {
        return registroPage.obtenerTextoNotificacion();
    }
}