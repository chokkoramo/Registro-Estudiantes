package definitions;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import steps.InicioSteps;

public class InicioDefinitions {
    private Playwright playwright;
    private Browser browser;
    private Page page;
    private InicioSteps inicioSteps;

    @Before
    public void setup(){
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newPage();

        inicioSteps = new InicioSteps(page);
    }

    @After
    public void cleanup(){
        if(browser != null) browser.close();
        if(playwright != null) playwright.close();
    }

    @Given("la pagina de inicio")
    public void laPaginaDeInicio() {
        inicioSteps.abrirPaginaInicio();
    }

}
