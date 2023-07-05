package sistemadetestes.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import sistemadetestes.pageObject.LoginPO;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LoginTest extends BaseTest {

    private static LoginPO loginPage;

    @BeforeClass
    public static void prepararTestes() {
        loginPage = new LoginPO(driver);
        driver.get("file:///home/danieldantasdev/IdeaProjects/selenium-lab/sistema/login.html");
    }

    @Test
    public void TC001_naoDeveLogarNoSistemaComEmailESenhaVazios() {
        loginPage.executarAcaoDeLogar("", "");
        String mensagem = loginPage.obterMensagem();
        assertEquals(mensagem, "Informe usuário e senha, os campos não podem ser brancos.");
    }

    @Test
    public void TC002_naoDeveLogarNoSistemaComEmailIncorretoESenhaVazia() {
        loginPage.executarAcaoDeLogar("teste", "");
        String mensagem = loginPage.obterMensagem();
        assertEquals(mensagem, "Informe usuário e senha, os campos não podem ser brancos.");
    }

    @Test
    public void TC003_naoDeveLogarNoSistemaComEmailVazioESenhaIncorreta() {
        loginPage.executarAcaoDeLogar("", "teste");
        String mensagem = loginPage.obterMensagem();
        assertEquals(mensagem, "Informe usuário e senha, os campos não podem ser brancos.");
    }


    @Test
    public void TC004_naoDeveLogarNoSistemaComEmailESenhaIncorretos() {
        loginPage.executarAcaoDeLogar("teste", "teste");
        String mensagem = loginPage.obterMensagem();
        assertEquals("E-mail ou senha inválidos", mensagem);
    }

    @Test
    public void TC005_naoDeveLogarNoSistemaComEmailIncorretoESenhaCorreta() {
        loginPage.executarAcaoDeLogar("admin@admin.com", "teste");
        String mensagem = loginPage.obterMensagem();
        assertEquals("E-mail ou senha inválidos", mensagem);
    }

    @Test
    public void TC006_naoDeveLogarNoSistemaComEmailCorretoESenhaIncorreta() {
        loginPage.executarAcaoDeLogar("teste", "admin@123");
        String mensagem = loginPage.obterMensagem();
        assertEquals("E-mail ou senha inválidos", mensagem);
    }

    @Test
    public void TC007_deveLogarNoSistemaComEmailESenhaCorretos() {
        loginPage.executarAcaoDeLogar("admin@admin.com", "admin@123");
        assertEquals(loginPage.obterTituloDaPagina(), "Controle de Produtos");
    }
}