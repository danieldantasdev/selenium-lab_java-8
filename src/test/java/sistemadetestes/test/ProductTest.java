package sistemadetestes.test;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import org.openqa.selenium.WebElement;
import sistemadetestes.pageObject.LoginPO;
import sistemadetestes.pageObject.ProductPO;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Classe de testes para a funcionalidade de produtos do sistema.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductTest extends BaseTest {

    private static LoginPO loginPage;
    private static ProductPO productPage;

    /**
     * Configuração dos testes, inicializando as páginas de login e de produtos.
     */
    @BeforeClass
    public static void prepararTestes() {
        loginPage = new LoginPO(driver);
        productPage = new ProductPO(driver);
//        loginPage.executarAcaoDeLogar("admin@admin.com", "admin@123");

        // Configuração do ambiente de teste
        driver.get("file:///home/danieldantasdev/IdeaProjects/selenium-lab/sistema/produtos.html");
    }

    /**
     * Teste para verificar se é possível voltar para a tela de login ao clicar na opção "Voltar" da barra de menu.
     */
    @Test
    public void TC001_deveVoltarParaTelaDeLoginAoClicarNaOpcaoVoltarDaBarraDeMenu() {
        productPage.clicarOpcaoVoltar();
        assertEquals("Login", productPage.obterTituloDaPagina());
    }

    /**
     * Teste para verificar se os produtos cadastrados são exibidos corretamente na tabela após o login.
     */
    @Test
    public void TC002_deveTrazerNaTabelaOsProdutosCadastradosAoSerRealizadoOLogin() {
        List<WebElement> productRows = productPage.obterLinhasProdutos();
        assertTrue(productRows.size() > 0);
    }

    /**
     * Teste para verificar se a tela de cadastro de produtos é aberta corretamente ao clicar no botão "Criar".
     */
    @Test
    public void TC003_aoClicarUmaVezNoBotaoCriarDeveAbrirATelaDeCadastroDosProdutos() {
        productPage.clicarBotaoCriar();
        assertTrue(productPage.telaCadastroEstaAberta());
    }

    /**
     * Teste para verificar se a tela de cadastro de produtos é fechada corretamente ao clicar no botão "Sair".
     */
    @Test
    public void TC004_deveFecharATelaDeCadastroDeProdutosAoClicarNoBotaoSair() {
        productPage.clicarBotaoCriar();
        productPage.clicarBotaoCriar();
        productPage.clicarBotaoSair();
        productPage.clicarBotaoSair();
        assertFalse(productPage.telaCadastroEstaAberta());
    }

    /**
     * Teste para verificar se é possível sair da mensagem de erro ao clicar no ícone "X".
     */
    @Test
    public void TC005_deveSairDaMensagemDeErroAoClicarNoIconeX() {
        productPage.clicarBotaoCriar();
        productPage.clicarBotaoCriar();
        productPage.clicarBotaoSalvar();
        productPage.fecharMensagemDeErro();
        assertFalse(productPage.mensagemDeErroEstaVisivel());
    }
}
