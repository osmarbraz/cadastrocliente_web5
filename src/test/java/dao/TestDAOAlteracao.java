package dao;

import controle.cliente.ClienteAlterar;
import dao.cliente.ClienteDAO;
import dao.cliente.SQLiteClienteDAO;
import entidade.Cliente;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Classe de testes unitários e de integração para a operação de alteração
 * de clientes nas diferentes implementações da camada de persistência.
 *
 * Os testes verificam o comportamento dos DAOs SQLite, HashMap e RAF,
 * contemplando alterações de nome e CPF, clientes inexistentes, clientes
 * nulos e tratamento de exceções no servlet e no DAO SQLite.
 *
 * Os testes utilizam JUnit 5 para execução e verificação dos resultados,
 * além de Mockito para simular dependências e condições excepcionais.
 */
class TestDAOAlteracao {

    /**
     * Cria um cliente de teste com identificador gerado a partir do tempo
     * do sistema, reduzindo a possibilidade de conflito entre execuções.
     *
     * @return instância de {@link Cliente} preenchida com dados de teste
     */
    private Cliente criarClienteTeste() {
        // Variáveis
        Cliente cliente = new Cliente();

        // Entrada
        int identificador = 1000000 + (int) (System.nanoTime() % 8000000);

        // Processamento
        cliente.setClienteId(String.valueOf(identificador));
        cliente.setNome("TesteAlteracao");
        cliente.setCpf("11111111111");

        // Saída
        return cliente;
    }

    /**
     * Cria um objeto de filtro contendo somente o identificador do cliente.
     *
     * O funcionamento deste filtro depende de a implementação de
     * {@code aplicarFiltro()} considerar os campos não nulos como critérios
     * de pesquisa.
     *
     * @param id identificador do cliente a ser pesquisado
     * @return objeto {@link Cliente} utilizado como filtro de pesquisa
     */
    private Cliente criarFiltroPorId(String id) {
        // Variáveis
        Cliente filtro = new Cliente();

        // Processamento
        filtro.setClienteId(id);

        // Saída
        return filtro;
    }

    /**
     * Verifica a alteração do nome de um cliente em uma implementação
     * específica da camada de persistência.
     *
     * O teste insere um cliente, recupera seu registro, altera o nome,
     * verifica o retorno da operação e consulta novamente o registro para
     * confirmar que o valor foi persistido.
     *
     * @param tipoDAO constante que identifica a implementação do DAO, como SQLite, HashMap ou RAF
     */
    private void testarAlteracaoNome(int tipoDAO) {
        // Variáveis
        DAOFactory factory = DAOFactory.getDAOFactory(tipoDAO);
        ClienteDAO dao = factory.getClienteDAO();
        Cliente cliente = criarClienteTeste();
        String nomeAlteracao = "NomeAlterado";

        try {
            // Entrada: insere o cliente
            assertTrue(dao.inserir(cliente),"A inserção do cliente deveria ser bem-sucedida.");

            // Processamento: localiza o cliente pelo identificador
            List<Cliente> lista = dao.aplicarFiltro(criarFiltroPorId(cliente.getClienteId()));

            assertFalse(lista.isEmpty(),"O cliente deveria existir antes da alteração." );

            Cliente clienteRecuperado = lista.get(0);
            clienteRecuperado.setNome(nomeAlteracao);

            // Processamento: altera o nome
            assertEquals(1,dao.alterar(clienteRecuperado),"A alteração deveria afetar um registro.");

            // Saída: consulta novamente e verifica o nome
            List<Cliente> listaAtualizada = dao.aplicarFiltro(criarFiltroPorId(cliente.getClienteId()));

            assertFalse(listaAtualizada.isEmpty(),"O cliente deveria continuar cadastrado após a alteração.");

            assertEquals(nomeAlteracao,listaAtualizada.get(0).getNome(),"O nome do cliente deveria ter sido alterado.");

        } finally {
            // Limpeza: remove o registro utilizado no teste
            dao.excluir(cliente);
        }
    }

    /**
     * Verifica a alteração do CPF de um cliente em uma implementação
     * específica da camada de persistência.
     *
     * O teste insere um cliente, recupera seu registro, altera o CPF,
     * verifica o retorno da operação e consulta novamente o registro para
     * confirmar que o CPF foi atualizado.
     *
     * @param tipoDAO constante que identifica a implementação do DAO, como SQLite, HashMap ou RAF
     */
    private void testarAlteracaoCpf(int tipoDAO) {
        // Variáveis
        DAOFactory factory = DAOFactory.getDAOFactory(tipoDAO);
        ClienteDAO dao = factory.getClienteDAO();
        Cliente cliente = criarClienteTeste();
        String cpfAlteracao = "22222222222";

        try {
            // Entrada: insere o cliente
            assertTrue(dao.inserir(cliente),"A inserção do cliente deveria ser bem-sucedida.");

            // Processamento: localiza o cliente pelo identificador
            List<Cliente> lista = dao.aplicarFiltro(criarFiltroPorId(cliente.getClienteId()));

            assertFalse(lista.isEmpty(),"O cliente deveria existir antes da alteração.");

            Cliente clienteRecuperado = lista.get(0);
            clienteRecuperado.setCpf(cpfAlteracao);

            // Processamento: altera o CPF
            assertEquals(1,dao.alterar(clienteRecuperado),"A alteração deveria afetar um registro.");

            // Saída: verifica o CPF atualizado
            List<Cliente> listaAtualizada = dao.aplicarFiltro(criarFiltroPorId(cliente.getClienteId()));

            assertFalse(listaAtualizada.isEmpty(),"O cliente deveria continuar cadastrado após a alteração.");

            assertEquals(cpfAlteracao,listaAtualizada.get(0).getCpf(),"O CPF do cliente deveria ter sido alterado.");

        } finally {
            // Limpeza: remove o registro utilizado no teste
            dao.excluir(cliente);
        }
    }

    /**
     * Testa a alteração do nome de um cliente na implementação SQLite.
     *
     * Delegação para o método auxiliar que executa as verificações
     * comuns de alteração do nome.
     */
    @Test
    void testAlteracaoNomeSQLite() {
        testarAlteracaoNome(DAOFactory.SQLITE);
    }

    /**
     * Testa a alteração do nome de um cliente na implementação HashMap.
     */
    @Test
    void testAlteracaoNomeHashMap() {
        testarAlteracaoNome(DAOFactory.HASHMAP);
    }

    /**
     * Testa a alteração do nome de um cliente na implementação RAF.
     */
    @Test
    void testAlteracaoNomeRAF() {
        testarAlteracaoNome(DAOFactory.RAF);
    }

    /**
     * Testa a alteração do CPF de um cliente na implementação SQLite.
     */
    @Test
    void testAlteracaoCpfSQLite() {
        testarAlteracaoCpf(DAOFactory.SQLITE);
    }

    /**
     * Testa a alteração do CPF de um cliente na implementação HashMap.
     */
    @Test
    void testAlteracaoCpfHashMap() {
        testarAlteracaoCpf(DAOFactory.HASHMAP);
    }

    /**
     * Testa a alteração do CPF de um cliente na implementação RAF.
     */
    @Test
    void testAlteracaoCpfRAF() {
        testarAlteracaoCpf(DAOFactory.RAF);
    }

    /**
     * Verifica que a implementação SQLite não altera um cliente inexistente.
     *
     * @implNote O teste pressupõe que o identificador gerado não esteja
     *           cadastrado no banco de dados.
     */
    @Test
    void testAlteracaoClienteNaoExistenteSQLite() {
        // Variáveis
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.SQLITE);
        ClienteDAO dao = factory.getClienteDAO();
        Cliente cliente = criarClienteTeste();

        // Processamento e saída
        assertEquals(0,dao.alterar(cliente),"A alteração de um cliente inexistente deveria retornar zero.");
    }

    /**
     * Verifica que a implementação HashMap não altera um cliente inexistente.
     */
    @Test
    void testAlteracaoClienteNaoExistenteHashMap() {
        // Variáveis
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.HASHMAP);
        ClienteDAO dao = factory.getClienteDAO();
        Cliente cliente = criarClienteTeste();

        // Processamento e saída
        assertEquals(0, dao.alterar(cliente),"A alteração de um cliente inexistente deveria retornar zero.");
    }

    /**
     * Verifica que a implementação RAF não altera um cliente inexistente.
     */
    @Test
    void testAlteracaoClienteNaoExistenteRAF() {
        // Variáveis
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.RAF);
        ClienteDAO dao = factory.getClienteDAO();
        Cliente cliente = criarClienteTeste();

        // Processamento e saída
        assertEquals(0,dao.alterar(cliente),"A alteração de um cliente inexistente deveria retornar zero.");
    }

    /**
     * Verifica o tratamento de um argumento nulo na implementação SQLite.
     */
    @Test
    void testAlteracaoClienteNuloSQLite() {
        // Variáveis
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.SQLITE);
        ClienteDAO dao = factory.getClienteDAO();

        // Processamento e saída
        assertEquals(0, dao.alterar(null));
    }

    /**
     * Verifica o tratamento de um argumento nulo na implementação HashMap.
     */
    @Test
    void testAlteracaoClienteNuloHashMap() {
        // Variáveis
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.HASHMAP);
        ClienteDAO dao = factory.getClienteDAO();

        // Processamento e saída
        assertEquals(0, dao.alterar(null));
    }

    /**
     * Verifica o tratamento de um argumento nulo na implementação RAF.
     */
    @Test
    void testAlteracaoClienteNuloRAF() {
        // Variáveis
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.RAF);
        ClienteDAO dao = factory.getClienteDAO();

        // Processamento e saída
        assertEquals(0, dao.alterar(null));
    }

    /**
     * Verifica o tratamento de {@link IOException} durante a execução
     * do método {@code doPost()} do servlet de alteração de clientes.
     *
     * O Mockito simula uma falha na obtenção do {@code PrintWriter}
     * da resposta HTTP. O teste verifica que o servlet tenta obter
     * o escritor da resposta.
     *
     * @throws Exception caso uma exceção não tratada seja propagada
     *                   durante a execução do servlet
     */
    @Test
    void alterarCatchIOException() throws Exception {
        // Variáveis
        ClienteAlterar servlet = new ClienteAlterar();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Entrada: simula falha ao obter o PrintWriter
        when(response.getWriter()).thenThrow(new IOException("Erro simulado"));

        // Processamento: executa o servlet
        servlet.doPost(request, response);

        // Saída: confirma a tentativa de obter o PrintWriter
        verify(response).getWriter();
    }

    /**
     * Verifica o tratamento de {@link SQLException} pelo DAO SQLite.
     *
     * O teste utiliza um spy da implementação SQLite e simula uma
     * falha na execução do comando SQL. O comportamento esperado é que
     * o método {@code alterar()} trate a exceção e retorne zero.
     *
     * @throws Exception caso ocorra uma exceção não prevista durante
     *                   a configuração ou execução do teste
     */
    @Test
    void tratarSQLExceptionNoAlterarSQLiteClienteDAO() throws Exception {
        // Variáveis
        SQLiteClienteDAO dao = spy(new SQLiteClienteDAO());
        Connection conexao = mock(Connection.class);
        Statement statement = mock(Statement.class);
        Cliente cliente = criarClienteTeste();

        // Entrada: substitui a conexão e simula erro SQL
        doReturn(conexao).when(dao).getConnection();
        when(conexao.createStatement()).thenReturn(statement);

        doThrow(new SQLException("Erro simulado")).when(statement).executeUpdate(anyString());

        // Processamento: tenta alterar o cliente
        int resultado = dao.alterar(cliente);

        // Saída: verifica o retorno e a execução do comando SQL
        assertEquals(0,resultado,"O DAO deveria retornar zero ao tratar uma SQLException.");

        verify(statement).executeUpdate(anyString());
    }
}
