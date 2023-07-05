package sistemadetestes.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Página de objetos para a funcionalidade de produtos do sistema.
 */
public class ProductPO extends BasePO {

    @FindBy(css = "#collapsibleNavbar > ul > li > a")
    private WebElement buttonVoltarLogin;

    @FindBy(id = "btn-adicionar")
    public WebElement buttonCriarProduto;

    @FindBy(id = "btn-salvar")
    public WebElement buttonSalvarProduto;

    @FindBy(id = "btn-sair")
    public WebElement buttonSair;

    @FindBy(css = "div.alert.alert-danger button.close")
    public WebElement buttonFecharMensagemErro;

    @FindBy(xpath = "//table/tbody/tr")
    public List<WebElement> tableProduto;

    @FindBy(id = "cadastro-produto")
    public WebElement divCadastro;

    @FindBy(css = "div.alert.alert-danger")
    public WebElement spamErro;

    /**
     * Construtor da classe.
     *
     * @param driver O driver do Selenium WebDriver.
     */
    public ProductPO(WebDriver driver) {
        super(driver);
    }

    /**
     * Clica na opção "Voltar" da barra de menu.
     */
    public void clicarOpcaoVoltar() {
        buttonVoltarLogin.click();
    }

    /**
     * Clica no botão "Criar".
     */
    public void clicarBotaoCriar() {
        buttonCriarProduto.click();
    }

    /**
     * Clica no botão "Salvar".
     */
    public void clicarBotaoSalvar() {
        buttonSalvarProduto.click();
    }

    /**
     * Clica no botão "Sair".
     */
    public void clicarBotaoSair() {
        buttonSair.click();
    }

    /**
     * Verifica se a tela de cadastro de produtos está aberta.
     *
     * @return true se a tela estiver aberta, false caso contrário.
     */
    public boolean telaCadastroEstaAberta() {
        return divCadastro.isDisplayed();
    }

    /**
     * Obtém as linhas de produtos da tabela.
     *
     * @return Uma lista de elementos WebElement representando as linhas de produtos.
     */
    public List<WebElement> obterLinhasProdutos() {
        return tableProduto;
    }

    /**
     * Fecha a mensagem de erro.
     */
    public void fecharMensagemDeErro() {
        buttonFecharMensagemErro.click();
    }

    /**
     * Verifica se a mensagem de erro está visível.
     *
     * @return true se a mensagem estiver visível, false caso contrário.
     */
    public boolean mensagemDeErroEstaVisivel() {
        return spamErro.isDisplayed();
    }

    /**
     * Obtém o título da página.
     *
     * @return O título da página.
     */
    public String obterTituloDaPagina() {
        return driver.getTitle();
    }

}
