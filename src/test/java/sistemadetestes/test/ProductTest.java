package sistemadetestes.test;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import org.openqa.selenium.By;
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
//        productPage.clicarBotaoCriar();
        productPage.clicarBotaoSair();
        productPage.clicarBotaoSair();
        assertFalse(productPage.telaCadastroEstaAberta());
    }

    /**
     * Teste para verificar se é possível sair da mensagem de erro ao clicar no ícone "X".
     */
    @Test
    public void TC005_deveSairDaMensagemDeErroAoClicarNoIconeX() {
//        productPage.clicarBotaoCriar();
        productPage.clicarBotaoCriar();
        productPage.clicarBotaoSalvar();
        productPage.fecharMensagemDeErro();
        assertFalse(productPage.mensagemDeErroEstaVisivel());
    }

    /**
     * Teste para verificar se é possível editar um produto existente.
     */
    @Test
    public void TC006_deveEditarUmProdutoExistente() {
        // Selecionar um produto existente para editar
        List<WebElement> productRows = productPage.obterLinhasProdutos();
        if (productRows.size() > 0) {
            WebElement produtoParaEditar = productRows.get(0);
            produtoParaEditar.click();

            // Realizar a edição do produto
            String novoNome = "Novo Nome";
            productPage.limparCampoNome();
            productPage.preencherCampoNome(novoNome);
            productPage.clicarBotaoSalvar();

            // Verificar se a edição foi bem-sucedida
            assertEquals(novoNome, produtoParaEditar.findElement(By.cssSelector("td:nth-child(2)")).getText());
        }
    }

    /**
     * Teste para verificar se é possível excluir um produto existente.
     */
    @Test
    public void TC007_deveExcluirUmProdutoExistente() {
        // Cria um produto para ser excluído
        String codigo = "1";
        String nomeProduto = "Produto Teste";
        String quantidadeProduto = "10";
        String valorProduto = "50.00";
        String dataCadastroProduto = "05/06/2023";

        // Clica no botão "Criar" para abrir a tela de cadastro de produtos
//        productPage.clicarBotaoCriar();
        productPage.clicarBotaoCriar();

        // Preenche os campos de cadastro de produtos
        productPage.preencherCampoCodigo(codigo);
        productPage.preencherCampoNome(nomeProduto);
        productPage.preencherCampoQuantidade(quantidadeProduto);
        productPage.preencherCampoValor(valorProduto);
        productPage.preencherCampoDataCadastro(dataCadastroProduto);

        // Clica no botão "Salvar" para cadastrar o produto
        productPage.clicarBotaoSalvar();

        // Verifica se o produto foi cadastrado corretamente
        assertTrue(productPage.verificarProdutoNaTabela(nomeProduto));

        // Clica no botão de exclusão (ícone de lixeira) do produto cadastrado
        productPage.clicarBotaoExclusaoProduto(nomeProduto);

        // Verifica se o produto foi excluído corretamente
        assertFalse(productPage.verificarProdutoNaTabela(nomeProduto));
    }

    /**
     * Teste para verificar se é possível adicionar um novo produto.
     */
    @Test
    public void TC008_deveAdicionarUmNovoProduto() {
        // Dados do novo produto
        String codigo = "1";
        String nomeProduto = "Novo Produto";
        String quantidadeProduto = "5";
        String valorProduto = "100.00";
        String dataCadastroProduto = "2023-06-23";


        // Verifica se o produto não está presente na tabela antes de adicionar
        assertFalse(productPage.verificarProdutoNaTabela(nomeProduto));

        // Clica no botão "Criar" para abrir a tela de cadastro de produtos
//        productPage.clicarBotaoCriar();
        productPage.clicarBotaoCriar();

        // Preenche os campos de cadastro de produtos
        productPage.preencherCampoCodigo(codigo);
        productPage.preencherCampoNome(nomeProduto);
        productPage.preencherCampoQuantidade(quantidadeProduto);
        productPage.preencherCampoValor(valorProduto);
        productPage.preencherCampoDataCadastro(dataCadastroProduto);

        // Clica no botão "Salvar" para cadastrar o produto
        productPage.clicarBotaoSalvar();

        // Verifica se o produto foi cadastrado corretamente
        assertTrue(productPage.verificarProdutoNaTabela(nomeProduto));
    }

    /**
     * Teste para verificar se a validação de campos obrigatórios funciona corretamente no cadastro de produtos.
     */
    @Test
    public void TC009_deveValidarCamposObrigatoriosNoCadastroDeProdutos() {

        // Clica no botão "Criar" para abrir a tela de cadastro de produtos
//        productPage.clicarBotaoCriar();
        productPage.clicarBotaoCriar();

        // Clica no botão "Salvar" sem preencher os campos obrigatórios
        productPage.clicarBotaoSalvar();

        // Verifica se a mensagem de erro está visível
        assertTrue(productPage.mensagemDeErroEstaVisivel());

        // Fecha a mensagem de erro
        productPage.fecharMensagemDeErro();

        // Verifica se a tela de cadastro ainda está aberta
        assertTrue(productPage.telaCadastroEstaAberta());
    }

    /**
     * Teste para verificar se é possível visualizar os detalhes de um produto.
     */
    @Test
    public void TC010_deveVisualizarOsDetalhesDeUmProduto() {
// Clica no botão "Criar" para abrir a tela de cadastro de produtos
        productPage.clicarBotaoCriar();

        // Preenche os campos obrigatórios
        String codigo = "1";
        String nomeProduto = "Produto Teste";
        String quantidadeProduto = "10";
        String valorProduto = "50.00";
        String dataCadastroProduto = "2023-06-23";

        productPage.preencherCampoCodigo(codigo);
        productPage.preencherCampoNome(nomeProduto);
        productPage.preencherCampoQuantidade(quantidadeProduto);
        productPage.preencherCampoValor(valorProduto);
        productPage.preencherCampoDataCadastro(dataCadastroProduto);

        // Clica no botão "Salvar" para cadastrar o produto
        productPage.clicarBotaoSalvar();
        productPage.clicarBotaoSalvar();

        // Clica no botão de visualização (ícone de olho) do produto cadastrado
        productPage.clicarBotaoVisualizacaoProduto(nomeProduto);

        // Verifica se a tela de detalhes está aberta corretamente
        assertTrue(productPage.telaDetalhesEstaAberta());

        // Verifica se os detalhes do produto estão corretos
        assertEquals(nomeProduto, productPage.obterNomeProdutoDetalhes());
        assertEquals(quantidadeProduto, productPage.obterQuantidadeProdutoDetalhes());
        assertEquals(valorProduto, productPage.obterValorProdutoDetalhes());
        assertEquals(dataCadastroProduto, productPage.obterDataCadastroProdutoDetalhes());
    }
}
