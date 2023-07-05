package sistemadetestes.pageObject;

import org.openqa.selenium.By;
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

    @FindBy(css = "div.card")
    public WebElement divDetalhes;

    @FindBy(id = "detalhes-nome")
    public WebElement spanNomeProdutoDetalhes;

    @FindBy(id = "detalhes-quantidade")
    public WebElement spanQuantidadeProdutoDetalhes;

    @FindBy(id = "detalhes-valor")
    public WebElement spanValorProdutoDetalhes;

    @FindBy(id = "detalhes-data")
    public WebElement spanDataCadastroProdutoDetalhes;

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

    /**
     * Limpa o campo de nome do produto.
     */
    public void limparCampoNome() {
        WebElement campoNome = driver.findElement(By.id("nome"));
        campoNome.clear();
    }

    /**
     * Preenche o campo de nome do produto com o valor especificado.
     *
     * @param codigo O nome do produto.
     */
    public void preencherCampoCodigo(String codigo) {
        WebElement campoCodigo = driver.findElement(By.id("codigo"));
        campoCodigo.sendKeys(codigo);
    }

    /**
     * Preenche o campo de nome do produto com o valor especificado.
     *
     * @param nome O nome do produto.
     */
    public void preencherCampoNome(String nome) {
        WebElement campoNome = driver.findElement(By.id("nome"));
        campoNome.sendKeys(nome);
    }

    /**
     * Preenche o campo de quantidade do produto com o valor especificado.
     *
     * @param quantidade A quantidade do produto.
     */
    public void preencherCampoQuantidade(String quantidade) {
        WebElement campoQuantidade = driver.findElement(By.id("quantidade"));
        campoQuantidade.sendKeys(quantidade);
    }

    /**
     * Preenche o campo de valor do produto com o valor especificado.
     *
     * @param valor O valor do produto.
     */
    public void preencherCampoValor(String valor) {
        WebElement campoValor = driver.findElement(By.id("valor"));
        campoValor.sendKeys(valor);
    }

    /**
     * Preenche o campo de data de cadastro do produto com o valor especificado.
     *
     * @param data A data de cadastro do produto.
     */
    public void preencherCampoDataCadastro(String data) {
        WebElement campoDataCadastro = driver.findElement(By.id("data"));
        campoDataCadastro.sendKeys(data);
    }

    /**
     * Obtém o texto da mensagem de erro.
     *
     * @return O texto da mensagem de erro.
     */
    public String obterTextoMensagemErro() {
        return spamErro.getText();
    }

    /**
     * Obtém o texto da última linha da tabela de produtos.
     *
     * @return O texto da última linha da tabela.
     */
    public String obterTextoUltimaLinhaTabela() {
        List<WebElement> rows = tableProduto;
        if (!rows.isEmpty()) {
            WebElement lastRow = rows.get(rows.size() - 1);
            return lastRow.getText();
        }
        return "";
    }

    /**
     * Verifica se um produto com o nome especificado está presente na tabela de produtos.
     *
     * @param nome O nome do produto.
     * @return true se o produto estiver presente, false caso contrário.
     */
    public boolean verificarProdutoNaTabela(String nome) {
        List<WebElement> rows = tableProduto;
        for (WebElement row : rows) {
            if (row.getText().contains(nome)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Clica no botão de edição (ícone de lápis) do último produto da tabela.
     */
    public void clicarBotaoEdicaoUltimoProduto() {
        List<WebElement> rows = tableProduto;
        if (!rows.isEmpty()) {
            WebElement lastRow = rows.get(rows.size() - 1);
            WebElement buttonEdit = lastRow.findElement(By.className("btn-edit"));
            buttonEdit.click();
        }
    }

    /**
     * Preenche o campo de nome do produto na tela de edição com o valor especificado.
     *
     * @param nome O novo nome do produto.
     */
    public void preencherCampoNomeEdicao(String nome) {
        WebElement campoNome = driver.findElement(By.id("edit-nome"));
        campoNome.clear();
        campoNome.sendKeys(nome);
    }

    /**
     * Preenche o campo de quantidade do produto na tela de edição com o valor especificado.
     *
     * @param quantidade A nova quantidade do produto.
     */
    public void preencherCampoQuantidadeEdicao(String quantidade) {
        WebElement campoQuantidade = driver.findElement(By.id("edit-quantidade"));
        campoQuantidade.clear();
        campoQuantidade.sendKeys(quantidade);
    }

    /**
     * Preenche o campo de valor do produto na tela de edição com o valor especificado.
     *
     * @param valor O novo valor do produto.
     */

    public void preencherCampoValorEdicao(String valor) {
        WebElement campoValor = driver.findElement(By.id("edit-valor"));
        campoValor.clear();
        campoValor.sendKeys(valor);
    }

    /**
     * Preenche o campo de data de cadastro do produto na tela de edição com o valor especificado.
     *
     * @param data A nova data de cadastro do produto.
     */
    public void preencherCampoDataCadastroEdicao(String data) {
        WebElement campoDataCadastro = driver.findElement(By.id("edit-data"));
        campoDataCadastro.clear();
        campoDataCadastro.sendKeys(data);
    }

    /**
     * Clica no botão "Salvar" na tela de edição.
     */
    public void clicarBotaoSalvarEdicao() {
        WebElement buttonSalvar = driver.findElement(By.id("btn-edit-salvar"));
        buttonSalvar.click();
    }

    /**
     * Verifica se a tela de edição está aberta.
     *
     * @return true se a tela de edição estiver aberta, false caso contrário.
     */
    public boolean telaEdicaoEstaAberta() {
        WebElement divEdicao = driver.findElement(By.id("edicao-produto"));
        return divEdicao.isDisplayed();
    }

    /**
     * Obtém o texto da mensagem de erro na tela de edição.
     *
     * @return O texto da mensagem de erro.
     */
    public String obterTextoMensagemErroEdicao() {
        WebElement spamErroEdicao = driver.findElement(By.cssSelector("div.alert.alert-danger.edicao"));
        return spamErroEdicao.getText();
    }

    /**
     * Clica no botão de exclusão (ícone de lixeira) do produto com o nome especificado.
     *
     * @param nome O nome do produto a ser excluído.
     */
    public void clicarBotaoExclusaoProduto(String nome) {
        List<WebElement> rows = tableProduto;
        for (WebElement row : rows) {
            List<WebElement> columns = row.findElements(By.tagName("td"));
            if (!columns.isEmpty() && columns.get(1).getText().equals(nome)) {
                WebElement buttonDelete = row.findElement(By.className("btn-delete"));
                buttonDelete.click();
                break;
            }
        }
    }

    /**
     * Verifica se a tela de detalhes do produto está aberta.
     *
     * @return true se a tela estiver aberta, false caso contrário.
     */
    public boolean telaDetalhesEstaAberta() {
        return divDetalhes.isDisplayed();
    }

    /**
     * Clica no botão de visualização (ícone de olho) do produto com o nome especificado.
     *
     * @param nome O nome do produto a ser visualizado.
     */
    public void clicarBotaoVisualizacaoProduto(String nome) {
        List<WebElement> rows = tableProduto;
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            if (cells.get(1).getText().equals(nome)) {
                cells.get(5).findElement(By.className("fa-eye")).click();
                break;
            }
        }
    }

    /**
     * Obtém o nome do produto exibido na tela de detalhes.
     *
     * @return O nome do produto.
     */
    public String obterNomeProdutoDetalhes() {
        return spanNomeProdutoDetalhes.getText();
    }

    /**
     * Obtém a quantidade do produto exibida na tela de detalhes.
     *
     * @return A quantidade do produto.
     */
    public String obterQuantidadeProdutoDetalhes() {
        return spanQuantidadeProdutoDetalhes.getText();
    }

    /**
     * Obtém o valor do produto exibido na tela de detalhes.
     *
     * @return O valor do produto.
     */
    public String obterValorProdutoDetalhes() {
        return spanValorProdutoDetalhes.getText();
    }

    /**
     * Obtém a data de cadastro do produto exibida na tela de detalhes.
     *
     * @return A data de cadastro do produto.
     */
    public String obterDataCadastroProdutoDetalhes() {
        return spanDataCadastroProdutoDetalhes.getText();
    }
}
